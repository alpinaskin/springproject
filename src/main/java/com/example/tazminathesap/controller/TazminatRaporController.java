package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.TazminatRaporService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/raporlar")
public class TazminatRaporController extends BaseController<TazminatRapor, TazminatRaporService> {

	protected TazminatRaporController(TazminatRaporService service, GenericModelAssembler<TazminatRapor> assembler) {
		super(service, assembler);
	}
	
}
