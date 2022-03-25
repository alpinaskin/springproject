package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.PasifDevreHesabi;
import com.example.tazminathesap.service.PasifDevreHesabiService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pasifdevre")
public class PasifDevreHesabiController extends BaseController<PasifDevreHesabi, PasifDevreHesabiService> {

    protected PasifDevreHesabiController(PasifDevreHesabiService service, GenericModelAssembler<PasifDevreHesabi> assembler) {
        super(service, assembler);
    }
}
