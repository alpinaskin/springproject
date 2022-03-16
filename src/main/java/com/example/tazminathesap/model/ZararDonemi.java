package com.example.tazminathesap.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
