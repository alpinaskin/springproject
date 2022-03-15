package com.example.tazminathesap.service;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;

public interface GelecekDevreHesabiService extends CrudService<GelecekDevreHesabi> {
    public GelecekDevreHesabi saveGelecekDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret);
}
