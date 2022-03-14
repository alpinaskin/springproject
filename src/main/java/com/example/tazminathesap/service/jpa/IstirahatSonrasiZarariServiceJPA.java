package com.example.tazminathesap.service.jpa;

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
    
}
