package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;

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
            .multiply(new BigDecimal(ekBilgiler.getMaluliyetOrani()))
            .multiply(new BigDecimal(ekBilgiler.getDavaliKusurOrani()))
            .multiply(new BigDecimal(1.95)); //yevmiye ve o günkü asgariücret katı
    }
}
