package com.example.tazminathesap.service;

import java.math.BigDecimal;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.EkBilgiler;

public interface IstirahatOncesiService {
    
    public BigDecimal istirahatOncesiDonemZarariHesapla(Long istirahatliGunSayisi, AsgariUcret asgariUcret, EkBilgiler ekBilgiler, Double yevmiye);
}
