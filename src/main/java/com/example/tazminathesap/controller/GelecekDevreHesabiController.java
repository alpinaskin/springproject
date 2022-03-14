package com.example.tazminathesap.controller;

import java.time.LocalDate;

import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.AsgariUcretService;
import com.example.tazminathesap.service.GelecekDevreHesabiService;
import com.example.tazminathesap.service.TazminatRaporService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gelecekdevre")
public class GelecekDevreHesabiController extends BaseController<GelecekDevreHesabi, GelecekDevreHesabiService>{

    private final AsgariUcretService asgariUcretService;
    private final TazminatRaporService tazminatRaporService;

    protected GelecekDevreHesabiController(GelecekDevreHesabiService service, AsgariUcretService asgariUcretService, TazminatRaporService tazminatRaporService) {
        super(service);
        this.asgariUcretService = asgariUcretService;
        this.tazminatRaporService = tazminatRaporService;
    }
    
    @GetMapping("/rapor/{id}")
    public ResponseEntity<GelecekDevreHesabi> calcGelecekDevreHesabi(@PathVariable("id") Long id){
        TazminatRapor tazminatRapor = tazminatRaporService.findById(id);
        tazminatRapor.getTarihBilgileri().getRaporTarihi();
        
        return null;
    }

    private LocalDate getLocalDateWithNextYear(){
        return null;
    }
}
