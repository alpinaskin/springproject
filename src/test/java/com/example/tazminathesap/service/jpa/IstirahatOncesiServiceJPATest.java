package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;

import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.service.IstirahatOncesiService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IstirahatOncesiServiceJPATest {
    
    @Autowired
    private IstirahatOncesiService service;
    
    @Test
    void testIstirahatOncesiDonemZarariHesapla() {
        //given
        Long istirahatliGunSayisi = 10L;
        BigDecimal netAsgariUcret = new BigDecimal(3_000);
        EkBilgiler ekBilgiler = new EkBilgiler(0.54,0.45,100.0,250.0,12500.0,true,2,true,true);

        //then
        BigDecimal zararToTest = service.istirahatOncesiDonemZarariHesapla(istirahatliGunSayisi, netAsgariUcret, ekBilgiler);
        //when
        //assertthat
    }
}
