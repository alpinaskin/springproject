package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.TazminatRaporService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raporlar")
public class TazminatRaporController extends BaseController<TazminatRapor, TazminatRaporService> {

	protected TazminatRaporController(TazminatRaporService service) {
		super(service);
	}
	
}
