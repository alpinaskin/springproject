package com.example.tazminathesap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class GecmisDevreHesabi extends BaseEntity{
    private String kazaTarihiRaporYiliSonu;
    private Double istirahatliDonemZarari;
    @JsonManagedReference
    @OneToMany(mappedBy = "gecmisDevreHesabi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IstirahatSonrasiZarari> istirahatSonrasiZarari = new ArrayList<>();
    private Double gecmisDevreZarari;

    public GecmisDevreHesabi(String kazaTarihiRaporYiliSonu, Double istirahatliDonemZarari, List<IstirahatSonrasiZarari> istirahatSonrasiZarari, Double gecmisDevreZarari) {
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

    public List<IstirahatSonrasiZarari> getIstirahatSonrasiZarari() {
        return this.istirahatSonrasiZarari;
    }

    public void setIstirahatSonrasiZarari(List<IstirahatSonrasiZarari> istirahatSonrasiZarari) {
        this.istirahatSonrasiZarari = istirahatSonrasiZarari;
    }

    public Double getGecmisDevreZarari() {
        return this.gecmisDevreZarari;
    }

    public void setGecmisDevreZarari(Double gecmisDevreZarari) {
        this.gecmisDevreZarari = gecmisDevreZarari;
    }

}
