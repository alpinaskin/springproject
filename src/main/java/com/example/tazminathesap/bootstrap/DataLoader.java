package com.example.tazminathesap.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.ERole;
import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.model.RaporBilgileri;
import com.example.tazminathesap.model.Role;
import com.example.tazminathesap.model.TarihBilgileri;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.model.UcretBilgileri;
import com.example.tazminathesap.model.User;
import com.example.tazminathesap.repository.RoleRepository;
import com.example.tazminathesap.repository.UserRepository;
import com.example.tazminathesap.service.AsgariUcretService;
import com.example.tazminathesap.service.jpa.EkBilgilerServiceJPA;
import com.example.tazminathesap.service.jpa.RaporBilgileriServiceJPA;
import com.example.tazminathesap.service.jpa.TarihBilgileriServiceJPA;
import com.example.tazminathesap.service.jpa.TazminatRaporServiceJPA;
import com.example.tazminathesap.service.jpa.UcretBilgileriServiceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
	private final EkBilgilerServiceJPA ekBilgilerService;
	private final RaporBilgileriServiceJPA raporBilgileriService;
	private final TarihBilgileriServiceJPA tarihBilgileriService;
	private final UcretBilgileriServiceJPA ucretBilgileriService;
	private final TazminatRaporServiceJPA tazminatRaporService;
	private final AsgariUcretService asgariUcretService;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	public DataLoader(EkBilgilerServiceJPA ekBilgilerService, 
					RaporBilgileriServiceJPA raporBilgileriService,
					TarihBilgileriServiceJPA tarihBilgileriService, 
					UcretBilgileriServiceJPA ucretBilgileriService,
					TazminatRaporServiceJPA tazminatRaporService,
					AsgariUcretService asgariUcretService,
					UserRepository userRepository,
					RoleRepository roleRepository) {
		super();
		this.ekBilgilerService = ekBilgilerService;
		this.raporBilgileriService = raporBilgileriService;
		this.tarihBilgileriService = tarihBilgileriService;
		this.ucretBilgileriService = ucretBilgileriService;
		this.tazminatRaporService = tazminatRaporService;
		this.asgariUcretService = asgariUcretService;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {


		/*** Admin kullanıcı oluştur */
		User adminUser = new User("admin", "", "admin@bilirkisi.com", encoder.encode("sifrem_1"));
		
		List<Role> roles = new ArrayList<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("rol yok?"));
		Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("rol yok?"));
		
		roles.add(userRole);
		roles.add(adminRole);
		
		adminUser.setRoles(roles);
		userRepository.save(adminUser);
		
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
			new BigDecimal(891.03)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2015,1,1),
			LocalDate.of(2015,6,30),
			new BigDecimal(949.07)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2015,7,1),
			LocalDate.of(2015,12,31),
			new BigDecimal(1000.54)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2016,1,1),
			LocalDate.of(2016,12,31),
			new BigDecimal(1300.99)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2017,1,1),
			LocalDate.of(2017,12,31),
			new BigDecimal(1404.06)
			));

		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2018,1,1),
			LocalDate.of(2018,12,31),
			new BigDecimal(1603.12)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2019,1,1),
			LocalDate.of(2019,12,31),
			new BigDecimal(2020.9)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2020,1,1),
			LocalDate.of(2020,12,31),
			new BigDecimal(2324.71)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2021,1,1),
			LocalDate.of(2021,12,31),
			new BigDecimal(2825.9)
			));
		asgariUcretService.save(new AsgariUcret(
			LocalDate.of(2022,1,1),
			LocalDate.of(2022,12,31),
			new BigDecimal(4253.4)
			));

		EkBilgiler ilkEkBilgiler = ekBilgilerService.save(new EkBilgiler(0.54,0.45,100.0,250.0,12500.0,true,2,true,true));
		
		RaporBilgileri ilkRaporBilgisi = raporBilgileriService.save(new RaporBilgileri("Ali Çevik","Ahmet Tan","Av. Mehmet Çetin","Av.Hüdaverdi Yılmaz","Murat Alık" ,"Ankara Asliye Ceza","2012/76"));
	
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
