package com.example.tazminathesap.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class ZararDonemi extends BaseEntity {
    LocalDate donemBaslangicTarihi;
    LocalDate donemBitisTarihi;
    Double donemZarar;

    public ZararDonemi() {
    }

}
