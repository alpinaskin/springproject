package com.example.tazminathesap.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PasifDevreHesabi extends BaseEntity{
    //Aktif Çalışma yaşı sonundan bakiye ömre kadar
    private String bakiyeOmur;
    private Double brutZarar;
    private Double bugunkuZarar;

}
