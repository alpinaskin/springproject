package com.example.tazminathesap.service;

import java.util.Date;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;

public interface AsgariUcretService extends CrudService<AsgariUcret>{
    
    public List<AsgariUcret> findAsgariUcretByDate(Date baslangicTarih, Date bitisTarih);
}
