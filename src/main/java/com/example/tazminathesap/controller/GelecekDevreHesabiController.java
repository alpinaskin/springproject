package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.service.GelecekDevreHesabiService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gelecekdevre")
public class GelecekDevreHesabiController extends BaseController<GelecekDevreHesabi, GelecekDevreHesabiService>{


    protected GelecekDevreHesabiController(GelecekDevreHesabiService service) {
        super(service);
    }
}
