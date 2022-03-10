package com.example.tazminathesap.service.jpa;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.repository.AsgariUcretRepository;
import com.example.tazminathesap.service.AsgariUcretService;

import org.springframework.stereotype.Service;

@Service
public class AsgariUcretServiceJPA extends AbstractJpaService<AsgariUcret, AsgariUcretRepository> implements AsgariUcretService{

    public AsgariUcretServiceJPA(AsgariUcretRepository repository) {
        super(repository);
    }
    
}
