package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.service.IstirahatOncesiService;
import com.example.tazminathesap.util.RaporFormatHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IstirahatOncesiServiceJPA implements IstirahatOncesiService {
    
    @Autowired
    RaporFormatHelper helper;

    public BigDecimal istirahatOncesiDonemZarariHesapla(Long istirahatliGunSayisi, AsgariUcret asgariUcret, EkBilgiler ekBilgiler, Double yevmiye)
    {   System.out.println(asgariUcret.getGunlukAsgariUcret());
        return new BigDecimal(asgariUcret.getGunlukAsgariUcret()*ekBilgiler.getDavaliKusurOrani()*istirahatliGunSayisi*((yevmiye/asgariUcret.getGunlukAsgariUcret()) > 1 ? yevmiye/asgariUcret.getGunlukAsgariUcret() : 1)).setScale(2, RoundingMode.CEILING);
        // return helper.getGunlukAsgariUcret(netAsgariUcret)
        // .multiply(new BigDecimal(ekBilgiler.getMaluliyetOrani()*ekBilgiler.getDavaliKusurOrani()*istirahatliGunSayisi*(yevmiye/netAsgariUcret.doubleValue()/30 > 1 ? yevmiye/netAsgariUcret.doubleValue() : 1))).setScale(3, RoundingMode.CEILING); //yevmiye ve o günkü asgariücret katı
    }
}
