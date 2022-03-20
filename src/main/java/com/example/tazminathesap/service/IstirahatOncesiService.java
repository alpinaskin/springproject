package com.example.tazminathesap.service;

import java.math.BigDecimal;

import com.example.tazminathesap.model.EkBilgiler;

public interface IstirahatOncesiService {
    
    public BigDecimal istirahatOncesiDonemZarariHesapla(Long istirahatliGunSayisi, BigDecimal netAsgariUcret, EkBilgiler ekBilgiler);
}
