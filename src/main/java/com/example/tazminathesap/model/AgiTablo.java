package com.example.tazminathesap.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgiTablo extends BaseEntity {
    private LocalDate yil;
    private Integer cocukSayisi;
    private boolean esCalismaDurumu;
    private Double agiAylik;
}
