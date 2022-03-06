package com.example.tazminathesap.bootstrap;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.model.RaporBilgileri;
import com.example.tazminathesap.model.TarihBilgileri;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.model.UcretBilgileri;
import com.example.tazminathesap.service.EkBilgilerService;
import com.example.tazminathesap.service.RaporBilgileriService;
import com.example.tazminathesap.service.TarihBilgileriService;
import com.example.tazminathesap.service.TazminatRaporService;
import com.example.tazminathesap.service.UcretBilgileriService;
import com.example.tazminathesap.service.jpa.EkBilgilerServiceJPA;
import com.example.tazminathesap.service.jpa.RaporBilgileriServiceJPA;
import com.example.tazminathesap.service.jpa.TarihBilgileriServiceJPA;
import com.example.tazminathesap.service.jpa.TazminatRaporServiceJPA;
import com.example.tazminathesap.service.jpa.UcretBilgileriServiceJPA;

@Component
public class DataLoader implements CommandLineRunner {
	private final EkBilgilerServiceJPA ekBilgilerService;
	private final RaporBilgileriServiceJPA raporBilgileriService;
	private final TarihBilgileriServiceJPA tarihBilgileriService;
	private final UcretBilgileriServiceJPA ucretBilgileriService;
	private final TazminatRaporServiceJPA tazminatRaporService;
	
	@Autowired
	public DataLoader(EkBilgilerServiceJPA ekBilgilerService, 
					RaporBilgileriServiceJPA raporBilgileriService,
					TarihBilgileriServiceJPA tarihBilgileriService, 
					UcretBilgileriServiceJPA ucretBilgileriService,
					TazminatRaporServiceJPA tazminatRaporService) {
		super();
		this.ekBilgilerService = ekBilgilerService;
		this.raporBilgileriService = raporBilgileriService;
		this.tarihBilgileriService = tarihBilgileriService;
		this.ucretBilgileriService = ucretBilgileriService;
		this.tazminatRaporService = tazminatRaporService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		EkBilgiler ilkEkBilgi = ekBilgilerService.save(new EkBilgiler(54,45,100.0,250.0,12500.0,true,2,true,true));
		RaporBilgileri ilkRaporBilgisi = raporBilgileriService.save(new RaporBilgileri("Ali","Ahmet","Mehmet","Hüdaverdi","Ankara Asliye Ceza","2012/76"));
		
		TarihBilgileri ilkTarihBilgisi = new TarihBilgileri();
		ilkTarihBilgisi.setDavaTarihi(LocalDate.of(2019, 10, 21));
		ilkTarihBilgisi.setIstirahatBitisTarihi(LocalDate.of(2009, 11, 5));
		ilkTarihBilgisi.setKazaliDogumTarihi(LocalDate.of(1975, 4, 12));
		ilkTarihBilgisi.setKazaTarihi(LocalDate.of(2013, 1, 12));
		ilkTarihBilgisi.setRaporTarihi(LocalDate.of(2020, 9, 11));
		ilkTarihBilgisi.setUcretTarihi(LocalDate.of(2017, 6, 30));
		
		UcretBilgileri ilkUcretBilgisi = ucretBilgileriService.save(new UcretBilgileri(10.0,20.0,50.0,23.2,67.2,20.45));
		
		TazminatRapor ilkTazminatRapor = new TazminatRapor();
		ilkTazminatRapor.setEkBilgiler(ilkEkBilgi);
		ilkTazminatRapor.setRaporBilgileri(ilkRaporBilgisi);
		ilkTazminatRapor.setTarihBilgileri(ilkTarihBilgisi);
		ilkTazminatRapor.setUcretBilgileri(ilkUcretBilgisi);
		
		tazminatRaporService.save(ilkTazminatRapor);
		System.out.println(ilkTarihBilgisi.toString());
	}
	
}
