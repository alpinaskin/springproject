package com.example.tazminathesap.service;

import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;

public interface GecmisDevreHesabiService extends CrudService<GecmisDevreHesabi> {
    public GecmisDevreHesabi saveGecmisDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret, List<AsgariUcret> asgariUcretList);
}
