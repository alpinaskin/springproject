package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;
import java.util.List;

import com.example.tazminathesap.model.IstirahatSonrasiZarari;
import com.example.tazminathesap.repository.IstirahatSonrasiZarariRepository;
import com.example.tazminathesap.service.IstirahatSonrasiService;

import org.springframework.stereotype.Service;

@Service
public class IstirahatSonrasiZarariServiceJPA extends AbstractJpaService<IstirahatSonrasiZarari, IstirahatSonrasiZarariRepository> implements IstirahatSonrasiService{

    public IstirahatSonrasiZarariServiceJPA(IstirahatSonrasiZarariRepository repository) {
        super(repository);
        //TODO Auto-generated constructor stub
    }

    public BigDecimal getToplamIstirahatSonrasiZarar(List<IstirahatSonrasiZarari> istirahatSonrasiZararList){
        return istirahatSonrasiZararList.stream()
            .map(e -> e.getTazminatMiktar())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
}
