package com.example.tazminathesap.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.tazminathesap.model.TarihBilgileri;
import com.example.tazminathesap.repository.TarihBilgileriRepository;
import com.example.tazminathesap.service.TarihBilgileriService;

@Service
public class TarihBilgileriServiceJPA extends AbstractJpaService <TarihBilgileri, TarihBilgileriRepository> implements TarihBilgileriService {

	public TarihBilgileriServiceJPA(TarihBilgileriRepository repository) {
		super(repository);
	}

}
