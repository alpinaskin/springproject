package com.example.tazminathesap.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ZararDonemi extends BaseEntity {
    LocalDate donemBaslangicTarihi;
    LocalDate donemBitisTarihi;
    BigDecimal donemZarar;
}
