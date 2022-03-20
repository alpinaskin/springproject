package com.example.tazminathesap.bootstrap;

import java.math.BigDecimal;
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
	
	@Override
	public void run(String... args) throws Exception {

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2010,1,1),
			LocalDate.of(2010,6,30),
			new BigDecimal(729)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2010,7,1),
			LocalDate.of(2010,12,31),
			new BigDecimal(760.50)
			));
	
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2011,1,1),
			LocalDate.of(2011,6,30),
			new BigDecimal(796.50)
			));
		
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2011,7,1),
			LocalDate.of(2011,12,31),
			new BigDecimal(837)
			));
					
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2012,1,1),
			LocalDate.of(2012,6,30),
			new BigDecimal(886.50)
			));
			
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2012,7,1),
			LocalDate.of(2012,12,31),
			new BigDecimal(940.50)
			));
		
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2013,1,1),
			LocalDate.of(2013,6,30),
			new BigDecimal(978.60)
			));
				
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2013,7,1),
			LocalDate.of(2013,12,31),
			new BigDecimal(1021.50)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2014,1,1),
			LocalDate.of(2014,6,30),
			new BigDecimal(1071)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2014,7,1),
			LocalDate.of(2014,12,31),
			new BigDecimal(1134)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2015,1,1),
			LocalDate.of(2015,6,30),
			new BigDecimal(1201.50)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2015,7,1),
			LocalDate.of(2015,12,31),
			new BigDecimal(1273.50)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2016,1,1),
			LocalDate.of(2016,12,31),
			new BigDecimal(1647)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2017,1,1),
			LocalDate.of(2017,12,31),
			new BigDecimal(1775.50)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2018,1,1),
			LocalDate.of(2018,12,31),
			new BigDecimal(2029.50)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2019,1,1),
			LocalDate.of(2019,12,31),
			new BigDecimal(2558.40)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2020,1,1),
			LocalDate.of(2020,12,31),
			new BigDecimal(2943)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2021,1,1),
			LocalDate.of(2021,12,31),
			new BigDecimal(3557.5)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2018,1,1),
			LocalDate.of(2018,12,31),
			new BigDecimal(5000.4)
			));

		EkBilgiler ilkEkBilgiler = ekBilgilerService.save(new EkBilgiler(0.54,0.45,100.0,250.0,12500.0,true,2,true,true));
		
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
