package com.example.tazminathesap.bootstrap;

import java.time.LocalDate;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.model.RaporBilgileri;
import com.example.tazminathesap.model.TarihBilgileri;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.model.UcretBilgileri;
import com.example.tazminathesap.service.AsgariUcretService;
import com.example.tazminathesap.service.jpa.EkBilgilerServiceJPA;
import com.example.tazminathesap.service.jpa.RaporBilgileriServiceJPA;
import com.example.tazminathesap.service.jpa.TarihBilgileriServiceJPA;
import com.example.tazminathesap.service.jpa.TazminatRaporServiceJPA;
import com.example.tazminathesap.service.jpa.UcretBilgileriServiceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
	private final EkBilgilerServiceJPA ekBilgilerService;
	private final RaporBilgileriServiceJPA raporBilgileriService;
	private final TarihBilgileriServiceJPA tarihBilgileriService;
	private final UcretBilgileriServiceJPA ucretBilgileriService;
	private final TazminatRaporServiceJPA tazminatRaporService;
	private final AsgariUcretService asgariUcretService;

	@Autowired
	public DataLoader(EkBilgilerServiceJPA ekBilgilerService, 
					RaporBilgileriServiceJPA raporBilgileriService,
					TarihBilgileriServiceJPA tarihBilgileriService, 
					UcretBilgileriServiceJPA ucretBilgileriService,
					TazminatRaporServiceJPA tazminatRaporService,
					AsgariUcretService asgariUcretService) {
		super();
		this.ekBilgilerService = ekBilgilerService;
		this.raporBilgileriService = raporBilgileriService;
		this.tarihBilgileriService = tarihBilgileriService;
		this.ucretBilgileriService = ucretBilgileriService;
		this.tazminatRaporService = tazminatRaporService;
		this.asgariUcretService = asgariUcretService;
	}
	
	public void run(String... args) throws Exception {

	
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2011,1,1),
			LocalDate.of(2011,6,30),
			796.50
			));
		
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2011,7,1),
			LocalDate.of(2011,12,31),
			837.
			));
					
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2012,1,1),
			LocalDate.of(2012,6,30),
			886.50
			));
			
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2012,7,1),
			LocalDate.of(2012,12,31),
			940.50
			));
		
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2013,1,1),
			LocalDate.of(2013,6,30),
			978.60
			));
				
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2013,7,1),
			LocalDate.of(2013,12,31),
			1021.50
			));

		EkBilgiler ilkEkBilgiler = ekBilgilerService.save(new EkBilgiler(54,45,100.0,250.0,12500.0,true,2,true,true));
		
		RaporBilgileri ilkRaporBilgisi = raporBilgileriService.save(new RaporBilgileri("Ali","Ahmet","Mehmet","Hüdaverdi","Ankara Asliye Ceza","2012/76"));
	
		TarihBilgileri ilkTarihBilgileri = tarihBilgileriService.save(new TarihBilgileri(LocalDate.of(2013, 12, 31),
				LocalDate.of(2009, 11, 5), 
				LocalDate.of(1975, 4, 12),
				LocalDate.of(2012, 1, 1),
				LocalDate.of(2012, 1, 1),
				LocalDate.of(2012, 1, 20)));
		
		UcretBilgileri ilkUcretBilgisi = ucretBilgileriService.save(new UcretBilgileri(10.0,20.0,50.0,23.2,67.2,20.45));
		
		TazminatRapor ilkTazminatRapor = new TazminatRapor(ilkRaporBilgisi, ilkUcretBilgisi, ilkTarihBilgileri, ilkEkBilgiler);
		ilkTazminatRapor.setId(1L);
		ilkRaporBilgisi.setTazminatRapor(ilkTazminatRapor);
		ilkUcretBilgisi.setTazminatRapor(ilkTazminatRapor);
		ilkTarihBilgileri.setTazminatRapor(ilkTazminatRapor);
		ilkEkBilgiler.setTazminatRapor(ilkTazminatRapor);
		tazminatRaporService.save(ilkTazminatRapor);
		
		
		System.out.println(ekBilgilerService.findAll());
		System.out.println(raporBilgileriService.findAll());
		System.out.println(tarihBilgileriService.findAll());
		System.out.println(tazminatRaporService.findAll());
		System.out.println(ucretBilgileriService.findAll());
		System.out.println(asgariUcretService.findAsgariUcretByDate(LocalDate.of(2012,12,31), LocalDate.of(2011,1,1)).toString());
	}
	
}
