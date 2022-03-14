package com.example.tazminathesap.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class GelecekDevreHesabi extends BaseEntity{
    
    private Double aktifDevreToplami;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true )
    @JoinColumn(name = "gelecek_devre_hesabi_id")
    private List<ZararDonemi> zararDonemleri;

    public GelecekDevreHesabi() {
    }

    public GelecekDevreHesabi(Double aktifDevreToplami, List<ZararDonemi> zararDonemleri) {
        this.aktifDevreToplami = aktifDevreToplami;
        this.zararDonemleri = zararDonemleri;
    }

    public Double getAktifDevreToplami() {
        return this.aktifDevreToplami;
    }

    public void setAktifDevreToplami(Double aktifDevreToplami) {
        this.aktifDevreToplami = aktifDevreToplami;
    }

    public List<ZararDonemi> getZararDonemleri() {
        return this.zararDonemleri;
    }

    public void setZararDonemleri(List<ZararDonemi> zararDonemleri) {
        this.zararDonemleri = zararDonemleri;
    }


}
