package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.service.GecmisDevreHesabiService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gecmisdevre")
public class GecmisDevreHesabiController extends BaseController<GecmisDevreHesabi, GecmisDevreHesabiService> {

    protected GecmisDevreHesabiController(GecmisDevreHesabiService service) {
        super(service);
    }
}
