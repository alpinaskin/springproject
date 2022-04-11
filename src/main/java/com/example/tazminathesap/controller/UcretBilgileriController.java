package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.UcretBilgileri;
import com.example.tazminathesap.service.UcretBilgileriService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ucretbilgileri")
public class UcretBilgileriController extends BaseController<UcretBilgileri, UcretBilgileriService> {

	protected UcretBilgileriController(UcretBilgileriService service, GenericModelAssembler<UcretBilgileri> assembler) {
		super(service, assembler);
	}
}
