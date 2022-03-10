package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class GecmisDevreHesabi extends BaseEntity{
    private String kazaTarihiRaporYiliSonu;
    private Double istirahatliDonemZarari;
    private Double istirahatSonrasiZarari;
    private Double gecmisDevreZarari;

}
