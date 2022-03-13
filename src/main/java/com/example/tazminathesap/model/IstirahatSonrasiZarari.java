package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class IstirahatSonrasiZarari {
    private Double tazminatMiktar;
    private String aciklama;

    public IstirahatSonrasiZarari(Double tazminatMiktar, String aciklama)
    {
        this.tazminatMiktar = tazminatMiktar;
        this.aciklama = aciklama;
    }

    public void setTazminatMiktar(Double tazminatMiktar)
    {
        this.tazminatMiktar = tazminatMiktar;
    }

    public void setAciklama(String aciklama){
        this.aciklama = aciklama;
    }

}
