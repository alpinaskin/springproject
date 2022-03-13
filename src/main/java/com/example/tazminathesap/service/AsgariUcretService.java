package com.example.tazminathesap.service;

import java.time.LocalDate;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;

public interface AsgariUcretService extends CrudService<AsgariUcret>{
    
    public List<AsgariUcret> findAsgariUcretByDate(LocalDate baslangicTarih, LocalDate bitisTarih);
    
    public AsgariUcret findAsgariUcretGivenDate(LocalDate tarih);
}
