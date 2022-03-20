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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PasifDevreHesabi extends BaseEntity{
    //Aktif Çalışma yaşı sonundan bakiye ömre kadar
    private LocalDate sonCalismaTarihi;
    private LocalDate bakiyeOmruTarihi;
    private BigDecimal brutZarar;
    private BigDecimal bugunkuZarar;

}
