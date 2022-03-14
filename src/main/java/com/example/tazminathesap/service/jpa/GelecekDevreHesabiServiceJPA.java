package com.example.tazminathesap.service.jpa;

import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.repository.GelecekDevreHesabiRepository;
import com.example.tazminathesap.service.GelecekDevreHesabiService;

import org.springframework.stereotype.Service;

@Service
public class GelecekDevreHesabiServiceJPA extends AbstractJpaService<GelecekDevreHesabi, GelecekDevreHesabiRepository> implements GelecekDevreHesabiService {

    public GelecekDevreHesabiServiceJPA(GelecekDevreHesabiRepository repository) {
        super(repository);
    }
    
    
}
