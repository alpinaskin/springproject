package com.example.tazminathesap.service.jpa;

import java.time.LocalDate;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.repository.AsgariUcretRepository;
import com.example.tazminathesap.service.AsgariUcretService;

import org.springframework.stereotype.Service;

@Service
public class AsgariUcretServiceJPA extends AbstractJpaService<AsgariUcret, AsgariUcretRepository> implements AsgariUcretService{

    public AsgariUcretServiceJPA(AsgariUcretRepository repository) {
        super(repository);
    }

    @Override
    public List<AsgariUcret> findAsgariUcretByDate(LocalDate baslangicTarih, LocalDate bitisTarih) {
        return repository.findAllAsgariUcretBetweenDates(baslangicTarih, bitisTarih);
    }

    @Override
    public AsgariUcret findAsgariUcretGivenDate(LocalDate tarih){
        return repository.findAsgariUcretByDate(tarih);
    }
    
}
