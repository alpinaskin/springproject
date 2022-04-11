package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.RaporBilgileri;
import com.example.tazminathesap.service.RaporBilgileriService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;;

@RestController
@RequestMapping("api/v1/raporbilgileri")
public class RaporBilgileriController extends BaseController<RaporBilgileri, RaporBilgileriService> {

	protected RaporBilgileriController(RaporBilgileriService service, GenericModelAssembler<RaporBilgileri> assembler) {
		super(service, assembler);
	}
}
