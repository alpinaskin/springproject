package com.example.tazminathesap.service.jpa;

import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.repository.GecmisDevreHesabiRepository;
import com.example.tazminathesap.service.GecmisDevreHesabiService;

import org.springframework.stereotype.Service;

@Service
public class GecmisDevreHesabiServiceJPA extends AbstractJpaService<GecmisDevreHesabi, GecmisDevreHesabiRepository> implements GecmisDevreHesabiService{

    public GecmisDevreHesabiServiceJPA(GecmisDevreHesabiRepository repository) {
        super(repository);
    }
    
}
