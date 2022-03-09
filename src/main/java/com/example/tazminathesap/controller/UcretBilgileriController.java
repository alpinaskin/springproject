package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.UcretBilgileri;
import com.example.tazminathesap.service.jpa.UcretBilgileriServiceJPA;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UcretBilgileriController {

	@Autowired
	private UcretBilgileriServiceJPA ucretBilgileriService;

	
	@GetMapping(value="/ucretbilgileri")
	public Set<UcretBilgileri> getUcretBilgileri() {
		
		return ucretBilgileriService.findAll();
	}
	
	@GetMapping(value="/ucretbilgileri/{id}")
	public UcretBilgileri getUcretBilgileriById(@PathVariable("id") Long id)
	{
		return ucretBilgileriService.findById(id);
	}
	
	@PostMapping(value="/ucretbilgileri")
	public void createUcretBilgileri(@RequestBody UcretBilgileri ucretBilgileri) {
		ucretBilgileriService.save(ucretBilgileri);
	}
	
	@PutMapping(value="/ucretbilgileri/{id}")
	public void updateUcretBilgileri(@RequestBody UcretBilgileri ucretBilgileri, @PathVariable("id") Long id)
	{
		//update işlemleri
	}
	
	@DeleteMapping(value="ucretBilgileri/{id}")
	public void deleteUcretBilgileri(@PathVariable("id") Long id)
	{
		ucretBilgileriService.findById(id);
	}
	
}
