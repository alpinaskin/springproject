package com.example.tazminathesap.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.model.IstirahatSonrasiZarari;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.AsgariUcretService;
import com.example.tazminathesap.service.GecmisDevreHesabiService;
import com.example.tazminathesap.service.TazminatRaporService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<GecmisDevreHesabi> calcGecmisDevreHesabi(@PathVariable("id") Long id)
    {
        GecmisDevreHesabi gecmisDevreHesabi = new GecmisDevreHesabi();
        TazminatRapor tazminatRapor = tazminatRaporService.findById(id);
        LocalDate istirahatBitisTarihi = tazminatRapor.getTarihBilgileri().getIstirahatBitisTarihi();
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();
        LocalDate raporTarihi = tazminatRapor.getTarihBilgileri().getRaporTarihi();
        
        Double asgariUcret = getAsgariUcretByDate(kazaTarihi);
        Double toplamZarar = 0.;

        //Kaza tarihi ve rapor yıl sonu arasındaki süreyi  hesapla
        gecmisDevreHesabi.setKazaTarihiRaporYiliSonu(ikiTarihArasiHesap(tazminatRapor));

        //istirahat süresi x kaza tarihindeki asgari ücret net        
        setDays(kazaTarihi, istirahatBitisTarihi);
        
        //kaza tarihindeki asgari ücreti bul, istirahat öncesi zarari hesapla
        gecmisDevreHesabi.setIstirahatliDonemZarari(istirahatOncesiDonemZarariHesapla(this.days.doubleValue(), asgariUcret, tazminatRapor.getEkBilgiler()));
        
        //İstirahat sonrası zararı hesapla
        gecmisDevreHesabi = istirahatSonrasiZararHesapla(gecmisDevreHesabi, istirahatBitisTarihi, raporTarihi);
        
        //Toplam Geçmiş Devre zararı hesapla
        toplamZarar = gecmisDevreHesabi.getIstirahatliDonemZarari();
        toplamZarar += gecmisDevreHesabi.getIstirahatSonrasiZarari().stream().mapToDouble(e -> e.getTazminatMiktar()).sum();
        //gecmisDevreHesabi.setGecmisDevreZarari(2500.0);
        
        return super.create(gecmisDevreHesabi);
    }


    private Double getAsgariUcretByDate(LocalDate tarih){
        return asgariUcretService.findAsgariUcretGivenDate(tarih).getAsgariUcretMiktar();
    }

    private Double istirahatOncesiDonemZarariHesapla(Double istirahatliGunSayisi, Double netAsgariUcret, EkBilgiler ekBilgiler)
    {
        return istirahatliGunSayisi*netAsgariUcret/30*ekBilgiler.getMaluliyetOrani()/100*ekBilgiler.getDavaliKusurOrani()/100*1.975;
    }

    private GecmisDevreHesabi istirahatSonrasiZararHesapla(GecmisDevreHesabi gecmisDevreHesabi, LocalDate istirahatBitisTarih, LocalDate raporTarih){
        List<IstirahatSonrasiZarari> istirahatSonrasiZarariList = new ArrayList<>();
        List<AsgariUcret> asgariUcretList = asgariUcretService.findAsgariUcretByDate(istirahatBitisTarih, raporTarih);
        asgariUcretList.forEach((asgariUcret) -> {
                    LocalDate yilSonu = endOfYear(asgariUcret.getBaslangicTarih());

                    if(tarihOnce(asgariUcret.getBaslangicTarih(), istirahatBitisTarih)){
                        //TODO: istirahat bitiş tarihinden başlayarak rapor tarihi yılının sonuna kadar günlük net asgari ücret toplamını bul
                        //aradaki günü bul. asgariucret/30 ile çarp. sonra tostring ile yazdır. 
                        // aradaki günü set et setDays();

                        if(asgariUcret.getBitisTarih().isBefore(yilSonu))
                            yilSonu = asgariUcret.getBitisTarih();

                        setDays(istirahatBitisTarih, yilSonu);
                        istirahatSonrasiZarariList.add(new IstirahatSonrasiZarari(asgariUcret.getAsgariUcretMiktar()/30*days.intValue(), "Tarih Başlangıç" + istirahatBitisTarih + " Tarih Bitiş: " + yilSonu + " GünxAsgariÜcret: " + this.days.intValue()+ " x " + asgariUcret.getAsgariUcretMiktar() + " Tazminat: " + asgariUcret.getAsgariUcretMiktar()/30*days.intValue(), gecmisDevreHesabi));
                    }
                    else {
                        //TODO: ilk tarih= asgari ücret başlangıc tarihi, son tarih rapor tarihinin sonu olmak üzere net asgari ücret toplamı bul
                        
                        if(asgariUcret.getBitisTarih().isBefore(yilSonu))
                            yilSonu = asgariUcret.getBitisTarih();

                        setDays(asgariUcret.getBaslangicTarih(), yilSonu);
                        istirahatSonrasiZarariList.add(new IstirahatSonrasiZarari(asgariUcret.getAsgariUcretMiktar()/30*days.intValue(), 
                        "Tarih Başlangıç" + asgariUcret.getBaslangicTarih() + " Tarih Bitiş: " + yilSonu + " GünxAsgariÜcret: " + this.days.intValue()+ " x " + asgariUcret.getAsgariUcretMiktar() + " Tazminat: " + asgariUcret.getAsgariUcretMiktar()/30*days.intValue(), gecmisDevreHesabi));
                    }   
                } 
            );
            //istirahatSonrasiService.findAll().forEach((istirahatSonrasiZarari) -> gecmisDevreHesabi.getIstirahatSonrasiZarari().add(istirahatSonrasiZarari));
            istirahatSonrasiZarariList.stream().forEach((e) -> gecmisDevreHesabi.getIstirahatSonrasiZarari().add(e));
            
            gecmisDevreHesabi.setGecmisDevreZarari(2500.0);
            return gecmisDevreHesabi;
    }

    private String ikiTarihArasiHesap(TazminatRapor tazminatRapor)
    {
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();
        LocalDate raporTarihiSonu = LocalDate.of(tazminatRapor.getTarihBilgileri().getRaporTarihi().getYear(), 12, 31);
        
        Period period = Period.between(kazaTarihi, raporTarihiSonu);
        String temp = "Yıl: " + period.getYears() + " Ay: " + period.getMonths() + " Gün: " + period.getDays();

        return temp;
    }

    private void setDays(LocalDate ilkTarih, LocalDate sonTarih)
    {
        this.days = ChronoUnit.DAYS.between(ilkTarih, sonTarih);
    }

    private boolean tarihOnce(LocalDate ilkTarih, LocalDate sonTarih){
        return ilkTarih.isBefore(sonTarih);
    }

    private LocalDate endOfYear(LocalDate date){
        return LocalDate.of(date.getYear(),12,31);
    }

}
