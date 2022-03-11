package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class GecmisDevreHesabi extends BaseEntity{
    private String kazaTarihiRaporYiliSonu;
    private Double istirahatliDonemZarari;
    private Double istirahatSonrasiZarari;
    private Double gecmisDevreZarari;

    public GecmisDevreHesabi(String kazaTarihiRaporYiliSonu, Double istirahatliDonemZarari, Double istirahatSonrasiZarari, Double gecmisDevreZarari) {
        this.kazaTarihiRaporYiliSonu = kazaTarihiRaporYiliSonu;
        this.istirahatliDonemZarari = istirahatliDonemZarari;
        this.istirahatSonrasiZarari = istirahatSonrasiZarari;
        this.gecmisDevreZarari = gecmisDevreZarari;
    }
    
    public GecmisDevreHesabi() {
    }

    public String getKazaTarihiRaporYiliSonu() {
        return this.kazaTarihiRaporYiliSonu;
    }

    public void setKazaTarihiRaporYiliSonu(String kazaTarihiRaporYiliSonu) {
        this.kazaTarihiRaporYiliSonu = kazaTarihiRaporYiliSonu;
    }

    public Double getIstirahatliDonemZarari() {
        return this.istirahatliDonemZarari;
    }

    public void setIstirahatliDonemZarari(Double istirahatliDonemZarari) {
        this.istirahatliDonemZarari = istirahatliDonemZarari;
    }

    public Double getIstirahatSonrasiZarari() {
        return this.istirahatSonrasiZarari;
    }

    public void setIstirahatSonrasiZarari(Double istirahatSonrasiZarari) {
        this.istirahatSonrasiZarari = istirahatSonrasiZarari;
    }

    public Double getGecmisDevreZarari() {
        return this.gecmisDevreZarari;
    }

    public void setGecmisDevreZarari(Double gecmisDevreZarari) {
        this.gecmisDevreZarari = gecmisDevreZarari;
    }

}
