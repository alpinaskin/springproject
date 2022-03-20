package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.EkBilgiler;
import com.example.tazminathesap.model.GecmisDevreHesabi;
import com.example.tazminathesap.model.IstirahatSonrasiZarari;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.repository.GecmisDevreHesabiRepository;
import com.example.tazminathesap.service.GecmisDevreHesabiService;
import com.example.tazminathesap.service.IstirahatOncesiService;
import com.example.tazminathesap.service.IstirahatSonrasiService;
import com.example.tazminathesap.util.RaporFormatHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GecmisDevreHesabiServiceJPA extends AbstractJpaService<GecmisDevreHesabi, GecmisDevreHesabiRepository> implements GecmisDevreHesabiService{

    private final BigDecimal AYDAKI_GUN_SAYISI = new BigDecimal(30);
    
    private final IstirahatOncesiService istirahatOncesiService;
    private final IstirahatSonrasiService istirahatSonrasiService;
    @Autowired
    RaporFormatHelper helper;

    public GecmisDevreHesabiServiceJPA(GecmisDevreHesabiRepository repository, IstirahatOncesiService istirahatOncesiService, IstirahatSonrasiService istirahatSonrasiService) {
        super(repository);
        this.istirahatOncesiService = istirahatOncesiService;
        this.istirahatSonrasiService = istirahatSonrasiService;
    }

    @Override
    public GecmisDevreHesabi saveGecmisDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret, List<AsgariUcret> asgariUcretList){
        
        GecmisDevreHesabi gecmisDevreHesabi = new GecmisDevreHesabi();
        LocalDate istirahatBitisTarihi = tazminatRapor.getTarihBilgileri().getIstirahatBitisTarihi();
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();
        LocalDate raporTarihi = tazminatRapor.getTarihBilgileri().getRaporTarihi();
        EkBilgiler ekBilgiler = tazminatRapor.getEkBilgiler();
        BigDecimal netAsgariUcret = asgariUcret.getAsgariUcretMiktar();
        //Kaza tarihi ve rapor yıl sonu arasındaki süreyi  hesapla
        gecmisDevreHesabi.setKazaTarihiRaporYiliSonu(helper.getIkiTarihArasi(tazminatRapor));

        //istirahat süresi x kaza tarihindeki asgari ücret net
        helper.setDays(kazaTarihi, istirahatBitisTarihi);        
        
        gecmisDevreHesabi.setIstirahatliDonemZarari(istirahatOncesiService.istirahatOncesiDonemZarariHesapla(helper.getDays(), netAsgariUcret, ekBilgiler));
        //İstirahat sonrası zararı hesapla
        gecmisDevreHesabi = istirahatSonrasiZararHesapla(gecmisDevreHesabi, istirahatBitisTarihi, raporTarihi, asgariUcretList);
        
        //Toplam Geçmiş Devre zararı hesapla
        gecmisDevreHesabi.setGecmisDevreZarari(
            istirahatSonrasiService.getToplamIstirahatSonrasiZarar(
                gecmisDevreHesabi.getIstirahatSonrasiZarari()
            )
        );
        
        return gecmisDevreHesabi;
    };

    private GecmisDevreHesabi istirahatSonrasiZararHesapla(GecmisDevreHesabi gecmisDevreHesabi, LocalDate istirahatBitisTarih, LocalDate raporTarih, List<AsgariUcret> asgariUcretList){

        List<IstirahatSonrasiZarari> istirahatSonrasiZarariList = asgariUcretList.stream()
                    .filter(asgariUcret -> asgariUcret.getBaslangicTarih().isBefore(istirahatBitisTarih))
                    .map(asgariUcret -> new IstirahatSonrasiZarari(helper.getGunlukAsgariUcret(asgariUcret.getAsgariUcretMiktar()), "", gecmisDevreHesabi))
                    .collect(Collectors.toList());

        asgariUcretList.stream()
            .filter(asgariUcret -> !(asgariUcret.getBaslangicTarih().isBefore(istirahatBitisTarih)))
            .map(asgariUcret -> new IstirahatSonrasiZarari(helper.getGunlukAsgariUcret(asgariUcret.getAsgariUcretMiktar())," " , gecmisDevreHesabi))
            .collect(Collectors.toList()).addAll(istirahatSonrasiZarariList);
            
        istirahatSonrasiZarariList.stream().forEach((e) -> gecmisDevreHesabi.getIstirahatSonrasiZarari().add(e));

        BigDecimal toplamGecmisDevreZarari = istirahatSonrasiZarariList.stream().map(e -> e.getTazminatMiktar()).reduce(BigDecimal.ZERO, BigDecimal::add);
        gecmisDevreHesabi.setGecmisDevreZarari(toplamGecmisDevreZarari);
        
        return gecmisDevreHesabi;
    }
}
