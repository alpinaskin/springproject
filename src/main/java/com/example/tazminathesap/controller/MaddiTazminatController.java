package com.example.tazminathesap.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.model.MaddiTazminat;
import com.example.tazminathesap.model.PasifDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.AsgariUcretService;
import com.example.tazminathesap.service.GecmisDevreHesabiService;
import com.example.tazminathesap.service.GelecekDevreHesabiService;
import com.example.tazminathesap.service.MaddiTazminatService;
import com.example.tazminathesap.service.PasifDevreHesabiService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/madditazminat")
public class MaddiTazminatController extends BaseController<MaddiTazminat, MaddiTazminatService> {
    private AsgariUcretService asgariUcretService;
    private GelecekDevreHesabiService gelecekDevreHesabiService;
    private GecmisDevreHesabiService gecmisDevreHesabiService;
    private PasifDevreHesabiService pasifDevreHesabiService;

    protected MaddiTazminatController(MaddiTazminatService service,
            GecmisDevreHesabiService gecmisDevreHesabiService, 
            GelecekDevreHesabiService gelecekDevreHesabiService, 
            PasifDevreHesabiService pasifDevreHesabiService, 
            AsgariUcretService asgariUcretService, GenericModelAssembler<MaddiTazminat> assembler) {
        super(service, assembler);
        this.gecmisDevreHesabiService = gecmisDevreHesabiService;
        this.gelecekDevreHesabiService = gelecekDevreHesabiService;
        this.pasifDevreHesabiService = pasifDevreHesabiService;
        this.asgariUcretService = asgariUcretService;
    }

    @PostMapping(value= "/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createMaddiTazminatByRapor(@RequestBody TazminatRapor tazminatRapor, Long userId)
    {
        
        LocalDate ucretTarihi = tazminatRapor.getTarihBilgileri().getUcretTarihi();
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();
        LocalDate raporTarihi = tazminatRapor.getTarihBilgileri().getRaporTarihi();
        LocalDate istirahatBitisTarihi = tazminatRapor.getTarihBilgileri().getIstirahatBitisTarihi();

        AsgariUcret asgariUcretKazaTarihinde = asgariUcretService.findAsgariUcretGivenDate(kazaTarihi); //Geçmiş Hesap için kaza tarihine göre asgari ücret
        AsgariUcret asgariUcretSonUcretTarihinde = asgariUcretService.findAsgariUcretGivenDate(ucretTarihi); //Gelecek Hesap için ücret tarihine göre asgari ücret
        AsgariUcret asgariUcretRaporTarihinde = asgariUcretService.findAsgariUcretGivenDate(raporTarihi); //Pasif Hesap için rapor tarihine göre asgari ücret
        List<AsgariUcret> asgariUcretList = asgariUcretService.findAsgariUcretByDate(istirahatBitisTarihi, raporTarihi);
        //List<Object[]> obj = asgariUcretService.findByDefauult(1L);
        
        if(asgariUcretKazaTarihinde == null || asgariUcretSonUcretTarihinde == null || asgariUcretRaporTarihinde == null || asgariUcretList == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        GecmisDevreHesabi gecmisDevreHesabi = gecmisDevreHesabiService.saveGecmisDevreHesabi(tazminatRapor, asgariUcretKazaTarihinde, asgariUcretList);
        GelecekDevreHesabi gelecekDevreHesabi = gelecekDevreHesabiService.saveGelecekDevreHesabi(tazminatRapor, asgariUcretSonUcretTarihinde);
        PasifDevreHesabi pasifDevreHesabi = pasifDevreHesabiService.createPasifDevreHesabiByTazminatIdAndAsgariUcret(tazminatRapor, asgariUcretRaporTarihinde);

        return super.create(new MaddiTazminat(tazminatRapor, gecmisDevreHesabi, gelecekDevreHesabi, pasifDevreHesabi));
    }
    
}
