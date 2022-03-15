package com.example.tazminathesap.service.jpa;

import com.example.tazminathesap.model.MaddiTazminat;
import com.example.tazminathesap.repository.MaddiTazminatRepository;
import com.example.tazminathesap.service.MaddiTazminatService;

import org.springframework.stereotype.Service;

@Service
public class MaddiTazminatServiceJPA extends AbstractJpaService<MaddiTazminat, MaddiTazminatRepository > implements MaddiTazminatService{

    public MaddiTazminatServiceJPA(MaddiTazminatRepository repository) {
        super(repository);
    }
    
}
