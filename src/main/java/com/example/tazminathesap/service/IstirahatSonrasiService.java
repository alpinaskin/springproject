package com.example.tazminathesap.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.tazminathesap.model.IstirahatSonrasiZarari;

public interface IstirahatSonrasiService extends CrudService<IstirahatSonrasiZarari> {
 
    public BigDecimal getToplamIstirahatSonrasiZarar(List<IstirahatSonrasiZarari> istirahatSonrasiZararList);
}
