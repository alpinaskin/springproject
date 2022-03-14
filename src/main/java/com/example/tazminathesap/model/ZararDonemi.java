package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class ZararDonemi extends BaseEntity {
    String zararDonemi;
    Double donemZarar;

    public ZararDonemi() {
    }

    public ZararDonemi(String zararDonemi, Double donemZarar) {
        this.zararDonemi = zararDonemi;
        this.donemZarar = donemZarar;
    }

    public String getZararDonemi() {
        return this.zararDonemi;
    }

    public void setZararDonemi(String zararDonemi) {
        this.zararDonemi = zararDonemi;
    }

    public Double getDonemZarar() {
        return this.donemZarar;
    }

    public void setDonemZarar(Double donemZarar) {
        this.donemZarar = donemZarar;
    }

}
