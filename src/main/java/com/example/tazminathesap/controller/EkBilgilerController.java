package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.service.EkBilgilerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ekbilgiler")
public class EkBilgilerController extends BaseController<EkBilgiler, EkBilgilerService> {
	
	protected EkBilgilerController(EkBilgilerService service) {
		super(service);
	}
}
