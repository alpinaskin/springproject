package com.example.tazminathesap.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class IstirahatSonrasiZarari extends BaseEntity{
    @JsonBackReference
    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name= "gecmisdevrehesabi_id", nullable = true)
    private GecmisDevreHesabi gecmisDevreHesabi;
    private Double tazminatMiktar;
    private String aciklama;

    public IstirahatSonrasiZarari(Double tazminatMiktar, String aciklama, GecmisDevreHesabi gecmisDevreHesabi)
    {
        this.tazminatMiktar = tazminatMiktar;
        this.aciklama = aciklama;
        this.gecmisDevreHesabi = gecmisDevreHesabi;
    }
    public IstirahatSonrasiZarari() {

    }
    
    public void setTazminatMiktar(Double tazminatMiktar)
    {
        this.tazminatMiktar = tazminatMiktar;
    }

    public void setAciklama(String aciklama){
        this.aciklama = aciklama;
    }

    public Double getTazminatMiktar() {
        return this.tazminatMiktar;
    }

    public String getAciklama() {
        return this.aciklama;
    }

    public GecmisDevreHesabi getGecmisDevreHesabi() {
        return this.gecmisDevreHesabi;
    }

    public void setGecmisDevreHesabi(GecmisDevreHesabi gecmisDevreHesabi) {
        this.gecmisDevreHesabi = gecmisDevreHesabi;
    }


}
