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

import com.example.tazminathesap.model.TarihBilgileri;
import com.example.tazminathesap.service.jpa.TarihBilgileriServiceJPA;

@RestController
public class TarihBilgileriController {
	
	@Autowired
	private TarihBilgileriServiceJPA tarihBilgileriService;
	
	@GetMapping(value="/tarihbilgileri")
	public Set<TarihBilgileri> getTarihBilgileri() {
		
		return tarihBilgileriService.findAll();
	}
	
	@GetMapping(value="/tarihbilgileri/{id}")
	public TarihBilgileri getTarihBilgileriById(@PathVariable("id") Long id) {
		return tarihBilgileriService.findById(id);
	}
	
	@PostMapping(value="/tarihbilgileri/{id}")
	public void createTarihBilgileri(@RequestBody TarihBilgileri tarihBilgileri)
	{
		tarihBilgileriService.save(tarihBilgileri);
	}
	
	@PutMapping(value="/tarihbilgileri")
	public void updateTarihBilgileri(@RequestBody TarihBilgileri tarihBilgileri, @PathVariable("id") Long id) {
		//update işlemleri
	}
	
	@DeleteMapping(value="/tarihbilgileri/{id}")
	public void deleteTarihBilgileriById(@PathVariable Long id) {
		tarihBilgileriService.deleteById(id);
	}
	
}
