package com.example.tazminathesap.service.jpa;

import org.springframework.stereotype.Service;

import com.example.tazminathesap.model.UcretBilgileri;
import com.example.tazminathesap.repository.UcretBilgileriRepository;
import com.example.tazminathesap.service.UcretBilgileriService;

@Service
public class UcretBilgileriServiceJPA extends AbstractJpaService <UcretBilgileri, UcretBilgileriRepository> implements UcretBilgileriService{

	public UcretBilgileriServiceJPA(UcretBilgileriRepository repository) {
		super(repository);	
	}

}
