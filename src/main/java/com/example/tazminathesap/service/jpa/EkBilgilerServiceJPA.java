package com.example.tazminathesap.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.repository.EkBilgilerRepository;
import com.example.tazminathesap.service.EkBilgilerService;

@Service
public class EkBilgilerServiceJPA extends AbstractJpaService<EkBilgiler, EkBilgilerRepository> implements EkBilgilerService {

	public EkBilgilerServiceJPA(EkBilgilerRepository repository) {
		super(repository);
	}

}
