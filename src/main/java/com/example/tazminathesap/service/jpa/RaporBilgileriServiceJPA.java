package com.example.tazminathesap.service.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.tazminathesap.model.RaporBilgileri;
import com.example.tazminathesap.repository.RaporBilgileriRepository;
import com.example.tazminathesap.service.RaporBilgileriService;

@Primary
@Service
public class RaporBilgileriServiceJPA extends AbstractJpaService<RaporBilgileri, RaporBilgileriRepository> implements RaporBilgileriService{

	public RaporBilgileriServiceJPA(RaporBilgileriRepository repository) {
		super(repository);
	}

}
