package com.example.tazminathesap.service;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.PasifDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;

public interface PasifDevreHesabiService extends CrudService<PasifDevreHesabi> {
 
    public PasifDevreHesabi savePasifDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret);
}
