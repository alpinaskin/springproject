package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;
import java.math.MathContext;

import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.service.IstirahatOncesiService;
import com.example.tazminathesap.util.RaporFormatHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IstirahatOncesiServiceJPA implements IstirahatOncesiService {
    
    @Autowired
    RaporFormatHelper helper;

    public BigDecimal istirahatOncesiDonemZarariHesapla(Long istirahatliGunSayisi, BigDecimal netAsgariUcret, EkBilgiler ekBilgiler)
    {
        return helper.getGunlukAsgariUcret(netAsgariUcret)
        .multiply(new BigDecimal(ekBilgiler.getMaluliyetOrani()*ekBilgiler.getDavaliKusurOrani()*1.95),new MathContext(4)); //yevmiye ve o günkü asgariücret katı
    }
}
