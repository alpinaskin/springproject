package com.example.tazminathesap.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.service.jpa.EkBilgilerServiceJPA;

@RestController
public class EkBilgilerController {
	
	@Autowired
	private EkBilgilerServiceJPA ekBilgilerService;
	
	@GetMapping(value="/ekbilgiler")
	public Set<EkBilgiler> getEkBilgiler() {
		return ekBilgilerService.findAll();
	}
	
	@GetMapping(value="/ekbilgiler/{id}")
	public EkBilgiler getEkBilgilerById(@PathVariable("id") Long id) {
		return ekBilgilerService.findById(id);
	}
	
	@PostMapping(value="/ekbilgiler")
	public void createEkBilgiler(@RequestBody EkBilgiler ekBilgiler) {
		ekBilgilerService.save(ekBilgiler);
	}
	
	@PutMapping(value="/ekbilgiler/{id}")
	public void updateEkBilgiler(@RequestBody EkBilgiler editedEkbilgiler, @PathVariable("id") Long id) {
		//EkBilgiler edit işlemleri
	}
	
	@DeleteMapping(value="/ekbilgiler/{id}")
	public void deleteEkBilgilerById(@PathVariable("id") Long id) {
		ekBilgilerService.deleteById(id);
	}
}
