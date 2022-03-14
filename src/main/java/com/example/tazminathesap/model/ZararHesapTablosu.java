package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class ZararHesapTablosu {
    private GecmisDevreHesabi gecmisDevreHesabi;
    private GelecekDevreHesabi gelecekDevreHesabi;
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
