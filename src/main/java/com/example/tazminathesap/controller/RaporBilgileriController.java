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

import com.example.tazminathesap.model.RaporBilgileri;
import com.example.tazminathesap.service.jpa.RaporBilgileriServiceJPA;;

@RestController
public class RaporBilgileriController {

	@Autowired
	private RaporBilgileriServiceJPA raporBilgileriService;
	
	@GetMapping(value="/raporbilgileri")
	public Set<RaporBilgileri> getRaporBilgileri() {
		return raporBilgileriService.findAll();
	}
	
	@PostMapping(value="/raporbilgileri")
	public void createRaporBilgileri(@RequestBody RaporBilgileri raporBilgileri) {
			raporBilgileriService.save(raporBilgileri);
	}
	
	@PutMapping(value="/raporbilgileri/{id}")
	public void updateRaporBilgileri(@RequestBody RaporBilgileri raporBilgileri, @PathVariable("id") Long id)
	{
		//update işlemleri
	}
	
	@DeleteMapping(value="/raporbilgileri/{id}")
	public void deleteRaporBilgileriById(@PathVariable("id") Long id)
	{
		raporBilgileriService.deleteById(id);
	}
	
	@GetMapping(value="/raporbilgileri/{id}")
	public RaporBilgileri getRaporBilgileriById(@PathVariable("id") Long id) {
		return raporBilgileriService.findById(id);
	}
}
