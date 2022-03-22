package com.example.tazminathesap.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class RaporFormatHelperTest {

    @Autowired
    RaporFormatHelper util;

    @Test
    void testGetAktifCalismaTarih() {

    }

    @Test
    void testGetGunlukAsgariUcret() {

        //given
        BigDecimal asgariUcret = new BigDecimal(3000);
        BigDecimal asgariUcret2 = new BigDecimal(3127.1);
        BigDecimal asgariUcret3 = new BigDecimal(75);
        BigDecimal asgariUcret4 = new BigDecimal(4563.564738465);
        //then
        Double asgariUcretGunluk = asgariUcret.doubleValue()/30;
        Double asgariUcretGunluk2 = asgariUcret2.doubleValue()/30;
        Double asgariUcretGunluk3 = asgariUcret3.doubleValue()/30;
        Double asgariUcretGunluk4 = asgariUcret4.doubleValue()/30;

        BigDecimal asgariUcretToTest = util.getGunlukAsgariUcret(asgariUcret);
        BigDecimal asgariUcretToTest2 = util.getGunlukAsgariUcret(asgariUcret2);
        BigDecimal asgariUcretToTest3 = util.getGunlukAsgariUcret(asgariUcret3);
        BigDecimal asgariUcretToTest4 = util.getGunlukAsgariUcret(asgariUcret4);
        
        System.out.println(round(asgariUcretGunluk4,3));
        System.out.println(asgariUcretToTest4.toString());
        //when
        Assert.isTrue(asgariUcretGunluk.equals(asgariUcretToTest.doubleValue()), "Eşit değil");
        Assert.isTrue(round(asgariUcretGunluk2,3).equals(asgariUcretToTest2.doubleValue()), "Eşit değil");
        Assert.isTrue(round(asgariUcretGunluk3,3).equals(asgariUcretToTest3.doubleValue()), "Eşit değil");
        Assert.isTrue(round(asgariUcretGunluk4,3).equals(asgariUcretToTest4.doubleValue()), "Eşit değil");


    }

    private static Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
