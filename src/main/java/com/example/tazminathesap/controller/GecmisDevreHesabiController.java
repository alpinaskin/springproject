package com.example.tazminathesap.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.AsgariUcretService;
import com.example.tazminathesap.service.GecmisDevreHesabiService;
import com.example.tazminathesap.service.TazminatRaporService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gecmisdevre")
public class GecmisDevreHesabiController extends BaseController<GecmisDevreHesabi, GecmisDevreHesabiService> {

    private final TazminatRaporService tazminatRaporService;
    private final AsgariUcretService asgariUcretService;
    private Long days = 0L; 

    protected GecmisDevreHesabiController(GecmisDevreHesabiService service, TazminatRaporService tazminatRaporService, AsgariUcretService asgariUcretService) {
        super(service);
        this.tazminatRaporService = tazminatRaporService;
        this.asgariUcretService = asgariUcretService;
    }

    @GetMapping("/rapor/{id}")
    @ResponseBody
    public ResponseEntity<GecmisDevreHesabi> calcGecmisDevreHesabi(@PathVariable("id") Long id)
    {
        GecmisDevreHesabi gecmisDevreHesabi = new GecmisDevreHesabi();
        //TODO: Kazatarihiyılsonu notunu hesapla
        TazminatRapor tazminatRapor = tazminatRaporService.findById(id);
        Period period = ikiTarihArasiHesap(tazminatRapor);
        gecmisDevreHesabi.setKazaTarihiRaporYiliSonu("Yıl: " + period.getYears() + " Ay: " + period.getMonths() + " Gün: " + period.getDays());

        //TODO: İstirahat dönem zararı hesapla
        //istirahat süresi x kaza tarihindeki asgari ücret net
        days = ChronoUnit.DAYS.between(tazminatRapor.getTarihBilgileri().getKazaTarihi(), tazminatRapor.getTarihBilgileri().getIstirahatBitisTarihi());
        //kaza tarihindeki asgari ücret
        Double asgariUcret = getAsgariUcretByDate(tazminatRapor.getTarihBilgileri().getKazaTarihi());
        logger.info("******            ASGARİ UCRET : " + asgariUcret);
        logger.info("******            IstirahatDonemZarari:" + istirahatOncesiDonemZarariHesapla(days.doubleValue(), asgariUcret, tazminatRapor.getEkBilgiler()));
        gecmisDevreHesabi.setIstirahatliDonemZarari(istirahatOncesiDonemZarariHesapla(days.doubleValue(), asgariUcret, tazminatRapor.getEkBilgiler()));
        //TODO: İstirahat sonrası zararı hesapla

        //TODO: Geçmiş Devre zararı hesapla
        return null;
        
    }

    private Period ikiTarihArasiHesap(TazminatRapor tazminatRapor)
    {
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();
        LocalDate raporTarihiSonu = LocalDate.of(tazminatRapor.getTarihBilgileri().getRaporTarihi().getYear(), 12, 31);
        Period period = Period.between(kazaTarihi, raporTarihiSonu);
        String temp = "Yıl: " + period.getYears() + " Ay: " + period.getMonths() + " Gün: " + period.getDays();

        return Period.between(kazaTarihi, raporTarihiSonu);
    }

    private Double getAsgariUcretByDate(LocalDate tarih){
        ZoneId defaultZoneId = ZoneId.systemDefault();

        Date date = Date.from(tarih.atStartOfDay(defaultZoneId).toInstant());
        return asgariUcretService.findAsgariUcretGivenDate(date).getAsgariUcretMiktar();
    }

    private Double istirahatOncesiDonemZarariHesapla(Double istirahaliGunSayisi, Double netAsgariUcret, EkBilgiler ekBilgiler)
    {
        return istirahaliGunSayisi*netAsgariUcret/30*ekBilgiler.getMaluliyetOrani()/100*ekBilgiler.getDavaliKusurOrani()/100*1.975;
    }

    private void istirahatSonrasiZararHesapla(Date istirahatBitisTarih, Date raporTarih){
        List<AsgariUcret> asgariUcretList = asgariUcretService.findAsgariUcretByDate(istirahatBitisTarih, raporTarih);

        asgariUcretList.forEach((asgariUcret) -> {
            if(tarihOnce(asgariUcret.getBaslangicTarih(), istirahatBitisTarih)){
                //TODO: istirahat bitiş tarihinden başlayarak rapor tarihi yılının sonuna kadar günlük net asgari ücret toplamını bul
                    //days = ChronoUnit.DAYS.between(asgariUcret.getBaslangicTarih().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.of(raporTarih.getYear(), 12, 31));

            }
            else {
                //TODO: ilk tarih= asgari ücret başlangıc tarihi, son tarih rapor tarihinin sonu olmak üzere net asgari ücret toplamı bul

            }   
                } 
            );

    }

    private boolean tarihOnce(Date ilkTarih, Date sonTarih){
        return ilkTarih.getTime() > sonTarih.getTime() ? true : false;
    }

}
