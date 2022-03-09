package com.example.tazminathesap.service.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.repository.TazminatRaporRepository;
import com.example.tazminathesap.service.TazminatRaporService;

@Primary
@Service
public class TazminatRaporServiceJPA extends AbstractJpaService<TazminatRapor, TazminatRaporRepository> implements TazminatRaporService {

	public TazminatRaporServiceJPA(TazminatRaporRepository repository) {
		super(repository);
	}

}
