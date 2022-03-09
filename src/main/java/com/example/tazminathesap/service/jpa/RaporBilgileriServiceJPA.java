package com.example.tazminathesap.service.jpa;

import org.springframework.stereotype.Service;

import com.example.tazminathesap.model.RaporBilgileri;
import com.example.tazminathesap.repository.RaporBilgileriRepository;
import com.example.tazminathesap.service.RaporBilgileriService;

@Service
public class RaporBilgileriServiceJPA extends AbstractJpaService<RaporBilgileri, RaporBilgileriRepository> implements RaporBilgileriService{

	public RaporBilgileriServiceJPA(RaporBilgileriRepository repository) {
		super(repository);
	}

}
