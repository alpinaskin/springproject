package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class PasifDevreHesabi extends BaseEntity{
    //Aktif Çalışma yaşı sonundan bakiye ömre kadar
    private String bakiyeOmur;
    private Double brutZarar;
    private Double bugunkuZarar;


    public PasifDevreHesabi() {
    }

    public PasifDevreHesabi(String bakiyeOmur, Double brutZarar, Double bugunkuZarar) {
        this.bakiyeOmur = bakiyeOmur;
        this.brutZarar = brutZarar;
        this.bugunkuZarar = bugunkuZarar;
    }

    public String getBakiyeOmur() {
        return this.bakiyeOmur;
    }

    public void setBakiyeOmur(String bakiyeOmur) {
        this.bakiyeOmur = bakiyeOmur;
    }

    public Double getBrutZarar() {
        return this.brutZarar;
    }

    public void setBrutZarar(Double brutZarar) {
        this.brutZarar = brutZarar;
    }

    public Double getBugunkuZarar() {
        return this.bugunkuZarar;
    }

    public void setBugunkuZarar(Double bugunkuZarar) {
        this.bugunkuZarar = bugunkuZarar;
    }

}
