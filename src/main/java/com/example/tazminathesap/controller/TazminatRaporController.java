package com.example.tazminathesap.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.jpa.TazminatRaporServiceJPA;

@RestController
public class TazminatRaporController {
	
	@Autowired
	private TazminatRaporServiceJPA tazminatRaporService;
	
	@GetMapping(value="/raporlar")
	public Set<TazminatRapor> getTazminatRapor() {
		return tazminatRaporService.findAll();
	}
	
	@PostMapping(value="/raporlar")
	public void createTazminatRapor(@RequestBody TazminatRapor tazminatRapor) {
		//rapor post işlemleri
		tazminatRaporService.save(tazminatRapor);
	}
	
	@PutMapping(value="/raporlar/{id}")
	public void updateTazminatRapor(@RequestBody TazminatRapor editedTazminatRapor, @PathVariable("id") Long id) {
		//Tazminat rapor edit işlemleri
	}
	
	@GetMapping(value="/raporlar/{id}")
	public TazminatRapor getTazminatRaporById(@PathVariable("id") Long id) {	
		return tazminatRaporService.findById(id);
	}
	
	@DeleteMapping(value="/raporlar/{id}")
	public void deleteTazminatRapor(@PathVariable("id") Long id) {
		tazminatRaporService.deleteById(id);
	}
}
