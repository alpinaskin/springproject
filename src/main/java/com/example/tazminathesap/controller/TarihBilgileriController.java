package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.TarihBilgileri;
import com.example.tazminathesap.service.TarihBilgileriService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tarihbilgileri")
public class TarihBilgileriController extends BaseController<TarihBilgileri, TarihBilgileriService> {

	protected TarihBilgileriController(TarihBilgileriService service, GenericModelAssembler<TarihBilgileri> assembler) {
		super(service, assembler);
	}		
}
