package com.example.tazminathesap.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ZararHesapTablosu extends BaseEntity{
    
    @JsonManagedReference
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="gecmis_devre_hesabi_id")
    private GecmisDevreHesabi gecmisDevreHesabi;
    @JsonManagedReference
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="gelecek_devre_hesabi_id")
    private GelecekDevreHesabi gelecekDevreHesabi;
    @JsonManagedReference
    @OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "pasif_devre_hesabi_id")
    private PasifDevreHesabi pasifDevreHesabi;
    private Double geciciIsGoremezlikTutari;
    private Double sgkAylikPesinDegeri;
    private Double nihaiZarar;


    public ZararHesapTablosu() {
    }

    public ZararHesapTablosu(GecmisDevreHesabi gecmisDevreHesabi, GelecekDevreHesabi gelecekDevreHesabi, PasifDevreHesabi pasifDevreHesabi, Double geciciIsGoremezlikTutari, Double sgkAylikPesinDegeri, Double nihaiZarar) {
        this.gecmisDevreHesabi = gecmisDevreHesabi;
        this.gelecekDevreHesabi = gelecekDevreHesabi;
        this.pasifDevreHesabi = pasifDevreHesabi;
        this.geciciIsGoremezlikTutari = geciciIsGoremezlikTutari;
        this.sgkAylikPesinDegeri = sgkAylikPesinDegeri;
        this.nihaiZarar = nihaiZarar;
    }

    public GecmisDevreHesabi getGecmisDevreHesabi() {
        return this.gecmisDevreHesabi;
    }

    public void setGecmisDevreHesabi(GecmisDevreHesabi gecmisDevreHesabi) {
        this.gecmisDevreHesabi = gecmisDevreHesabi;
    }

    public GelecekDevreHesabi getGelecekDevreHesabi() {
        return this.gelecekDevreHesabi;
    }

    public void setGelecekDevreHesabi(GelecekDevreHesabi gelecekDevreHesabi) {
        this.gelecekDevreHesabi = gelecekDevreHesabi;
    }

    public PasifDevreHesabi getPasifDevreHesabi() {
        return this.pasifDevreHesabi;
    }

    public void setPasifDevreHesabi(PasifDevreHesabi pasifDevreHesabi) {
        this.pasifDevreHesabi = pasifDevreHesabi;
    }

    public Double getGeciciIsGoremezlikTutari() {
        return this.geciciIsGoremezlikTutari;
    }

    public void setGeciciIsGoremezlikTutari(Double geciciIsGoremezlikTutari) {
        this.geciciIsGoremezlikTutari = geciciIsGoremezlikTutari;
    }

    public Double getSgkAylikPesinDegeri() {
        return this.sgkAylikPesinDegeri;
    }

    public void setSgkAylikPesinDegeri(Double sgkAylikPesinDegeri) {
        this.sgkAylikPesinDegeri = sgkAylikPesinDegeri;
    }

    public Double getNihaiZarar() {
        return this.nihaiZarar;
    }

    public void setNihaiZarar(Double nihaiZarar) {
        this.nihaiZarar = nihaiZarar;
    }

}
