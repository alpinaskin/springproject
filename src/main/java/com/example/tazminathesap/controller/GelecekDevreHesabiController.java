package com.example.tazminathesap.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.AsgariUcretService;
import com.example.tazminathesap.service.GelecekDevreHesabiService;
import com.example.tazminathesap.service.TazminatRaporService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gelecekdevre")
public class GelecekDevreHesabiController extends BaseController<GelecekDevreHesabi, GelecekDevreHesabiService>{

    private final AsgariUcretService asgariUcretService;
    private final TazminatRaporService tazminatRaporService;

    protected GelecekDevreHesabiController(GelecekDevreHesabiService service, AsgariUcretService asgariUcretService, TazminatRaporService tazminatRaporService) {
        super(service);
        this.asgariUcretService = asgariUcretService;
        this.tazminatRaporService = tazminatRaporService;
    }
    
    @GetMapping("/rapor/{id}")
    public ResponseEntity<GelecekDevreHesabi> calcGelecekDevreHesabi(@PathVariable("id") Long id){
        TazminatRapor tazminatRapor = tazminatRaporService.findById(id);
        LocalDate dogumTarihi = tazminatRapor.getTarihBilgileri().getKazaliDogumTarihi();
        LocalDate ucretTarihi = tazminatRapor.getTarihBilgileri().getUcretTarihi();
        Double yevmiye = tazminatRapor.getUcretBilgileri().getGunlukCiplakYevmiye();
        LocalDate raporTarihi = tazminatRapor.getTarihBilgileri().getRaporTarihi();
        AsgariUcret asgariUcret = asgariUcretService.findAsgariUcretGivenDate(ucretTarihi);
        LocalDate sonAktifCalismaTarih = getAktifCalismaTarih(dogumTarihi);
        logger.info(asgariUcret + "   "  +  getLocalDateWithNextYear(raporTarihi));


        gelecekDevreHesapla(getLocalDateWithNextYear(raporTarihi), sonAktifCalismaTarih);

        /**
         * Son yevmiyeyi bul
         * Son yevmiye yılındaki asgari ücreti bul
         * Rapor yılının sonrası yılı al
         * Her yıl için günlük asgari net ücret*aradaki gün*asgari ücret ve yevmiye katı ile tazminat bul
         * Son çalışma yaşı sonuna kadar hesapla
         */

        return null;
    }

    private void gelecekDevreHesapla(LocalDate baslangic, LocalDate bitis){
        //TODO: Gelecek devre raporu her yıl için hesapla
        while(tarihOnce(baslangic, bitis)){
            //TODO: İşlemler...
            
            if(!(baslangic.getYear() == bitis.getYear()))
                logger.info(baslangic+" "+ baslangic.with(TemporalAdjusters.lastDayOfYear()));
            else
                logger.info(baslangic+" "+ bitis);
            
           baslangic = baslangic.plusYears(1);
        }
    }

    private LocalDate getLocalDateWithNextYear(LocalDate raporTarihi){
        return raporTarihi.plusDays(1);
    }

    private LocalDate getAktifCalismaTarih(LocalDate dogumTarihi){
        return dogumTarihi.plusYears(60);
    }

    private boolean tarihOnce(LocalDate ilkTarih, LocalDate sonTarih){
        return ilkTarih.isBefore(sonTarih);
    }
}
