package com.example.tazminathesap.service.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.service.IstirahatOncesiService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IstirahatOncesiServiceJPATest {
    
    @Autowired
    private IstirahatOncesiService service;
    
    @Test
    void testIstirahatOncesiDonemZarariHesapla() {
        //given
        Long istirahatliGunSayisi = 1L;
        BigDecimal netAsgariUcret = new BigDecimal(3000);
        EkBilgiler ekBilgiler = new EkBilgiler(1.,1.,100.0,250.0,12500.0,true,2,true,true);
        EkBilgiler ekBilgiler2 = new EkBilgiler(0.54,0.75,100.0,250.0,12500.0,true,2,true,true);

        Double a = istirahatOncesiHesapTestMetod(1L, 3000., 1, 1);
        Double b = istirahatOncesiHesapTestMetod(60L, 3120.5, 0.54,0.75);
        //then
        BigDecimal zararToTest = service.istirahatOncesiDonemZarariHesapla(istirahatliGunSayisi, netAsgariUcret, ekBilgiler);
        BigDecimal zararToTest2 = service.istirahatOncesiDonemZarariHesapla(60L, new BigDecimal("3120.5"), ekBilgiler2);
        //when
        assertThat(zararToTest.doubleValue()).isNotNull().isEqualTo(a);        
        assertThat(zararToTest2.intValue()).isNotNull().isEqualTo(b.intValue());
    }

    private Double istirahatOncesiHesapTestMetod(Long gun, Double aylikAsgariUcret, double kusurOrani, double maluliyetOrani ){
        return (aylikAsgariUcret*gun*kusurOrani*maluliyetOrani*1.95)/30;
    }
}
