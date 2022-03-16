package com.example.tazminathesap.service.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.model.IstirahatSonrasiZarari;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.repository.GecmisDevreHesabiRepository;
import com.example.tazminathesap.service.GecmisDevreHesabiService;
import com.example.tazminathesap.util.RaporFormatHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GecmisDevreHesabiServiceJPA extends AbstractJpaService<GecmisDevreHesabi, GecmisDevreHesabiRepository> implements GecmisDevreHesabiService{

    @Autowired
    RaporFormatHelper helper;

    public GecmisDevreHesabiServiceJPA(GecmisDevreHesabiRepository repository) {
        super(repository);
    }

    @Override
    public GecmisDevreHesabi saveGecmisDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret, List<AsgariUcret> asgariUcretList){
        
        GecmisDevreHesabi gecmisDevreHesabi = new GecmisDevreHesabi();
        LocalDate istirahatBitisTarihi = tazminatRapor.getTarihBilgileri().getIstirahatBitisTarihi();
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();
        LocalDate raporTarihi = tazminatRapor.getTarihBilgileri().getRaporTarihi();
        
        Double toplamZarar = 0.;

        //Kaza tarihi ve rapor yıl sonu arasındaki süreyi  hesapla
        gecmisDevreHesabi.setKazaTarihiRaporYiliSonu(helper.getIkiTarihArasi(tazminatRapor));

        //istirahat süresi x kaza tarihindeki asgari ücret net
        helper.setDays(kazaTarihi, istirahatBitisTarihi);        
        
        //kaza tarihindeki asgari ücreti bul, istirahat öncesi zarari hesapla
        gecmisDevreHesabi.setIstirahatliDonemZarari(istirahatOncesiDonemZarariHesapla(helper.getDays().doubleValue(), asgariUcret.getAsgariUcretMiktar(), tazminatRapor.getEkBilgiler()));
        
        //İstirahat sonrası zararı hesapla
        gecmisDevreHesabi = istirahatSonrasiZararHesapla(gecmisDevreHesabi, istirahatBitisTarihi, raporTarihi, asgariUcretList);
        
        //Toplam Geçmiş Devre zararı hesapla
        toplamZarar = gecmisDevreHesabi.getIstirahatliDonemZarari();
        toplamZarar += gecmisDevreHesabi.getIstirahatSonrasiZarari().stream().mapToDouble(e -> e.getTazminatMiktar()).sum();
        gecmisDevreHesabi.setGecmisDevreZarari(toplamZarar);
        
        return gecmisDevreHesabi;
    };

    private Double istirahatOncesiDonemZarariHesapla(Double istirahatliGunSayisi, Double netAsgariUcret, EkBilgiler ekBilgiler)
    {
        return istirahatliGunSayisi*netAsgariUcret/30*ekBilgiler.getMaluliyetOrani()/100*ekBilgiler.getDavaliKusurOrani()/100*1.975;
    }

    private GecmisDevreHesabi istirahatSonrasiZararHesapla(GecmisDevreHesabi gecmisDevreHesabi, LocalDate istirahatBitisTarih, LocalDate raporTarih, List<AsgariUcret> asgariUcretList){
        List<IstirahatSonrasiZarari> istirahatSonrasiZarariList = new ArrayList<>();

        asgariUcretList.forEach((asgariUcret) -> {
                    LocalDate yilSonu = helper.getYilSonu(asgariUcret.getBaslangicTarih());

                    if(helper.tarihOnce(asgariUcret.getBaslangicTarih(), istirahatBitisTarih)){
                        //TODO: istirahat bitiş tarihinden başlayarak rapor tarihi yılının sonuna kadar günlük net asgari ücret toplamını bul
                        //aradaki günü bul. asgariucret/30 ile çarp. sonra tostring ile yazdır. 
                        // aradaki günü set et setDays();

                        if(asgariUcret.getBitisTarih().isBefore(yilSonu))
                            yilSonu = asgariUcret.getBitisTarih();

                        helper.setDays(istirahatBitisTarih, yilSonu);
                        istirahatSonrasiZarariList.add(new IstirahatSonrasiZarari(asgariUcret.getAsgariUcretMiktar()/30*helper.getDays().intValue(), "Tarih Başlangıç" + istirahatBitisTarih + " Tarih Bitiş: " + yilSonu + " GünxAsgariÜcret: " + helper.getDays().intValue()+ " x " + asgariUcret.getAsgariUcretMiktar() + " Tazminat: " + asgariUcret.getAsgariUcretMiktar()/30*helper.getDays().intValue(), gecmisDevreHesabi));
                    }
                    else {
                        //TODO: ilk tarih= asgari ücret başlangıc tarihi, son tarih rapor tarihinin sonu olmak üzere net asgari ücret toplamı bul
                        
                        if(asgariUcret.getBitisTarih().isBefore(yilSonu))
                            yilSonu = asgariUcret.getBitisTarih();

                        helper.setDays(asgariUcret.getBaslangicTarih(), yilSonu);
                        istirahatSonrasiZarariList.add(new IstirahatSonrasiZarari(asgariUcret.getAsgariUcretMiktar()/30*helper.getDays().intValue(), 
                        "Tarih Başlangıç" + asgariUcret.getBaslangicTarih() + " Tarih Bitiş: " + yilSonu + " GünxAsgariÜcret: " + helper.getDays().intValue()+ " x " + asgariUcret.getAsgariUcretMiktar() + " Tazminat: " + asgariUcret.getAsgariUcretMiktar()/30*helper.getDays().intValue(), gecmisDevreHesabi));
                    }   
                } 
            );
            //istirahatSonrasiService.findAll().forEach((istirahatSonrasiZarari) -> gecmisDevreHesabi.getIstirahatSonrasiZarari().add(istirahatSonrasiZarari));
            istirahatSonrasiZarariList.stream().forEach((e) -> gecmisDevreHesabi.getIstirahatSonrasiZarari().add(e));
            
            gecmisDevreHesabi.setGecmisDevreZarari(2500.0);
            return gecmisDevreHesabi;
    }
}
