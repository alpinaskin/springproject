package com.example.tazminathesap.service.jpa;

import java.time.LocalDate;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.PasifDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.repository.PasifDevreHesabiRepository;
import com.example.tazminathesap.service.PasifDevreHesabiService;
import com.example.tazminathesap.util.RaporFormatHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasifDevreHesabiServiceJPA extends AbstractJpaService<PasifDevreHesabi, PasifDevreHesabiRepository> implements PasifDevreHesabiService {

    @Autowired
    RaporFormatHelper helper;

    public PasifDevreHesabiServiceJPA(PasifDevreHesabiRepository repository) {
        super(repository);
    }

    public PasifDevreHesabi savePasifDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret){
        Double kusurOrani = tazminatRapor.getEkBilgiler().getDavaliKusurOrani().doubleValue();
        Double maluliyet = tazminatRapor.getEkBilgiler().getDavaliKusurOrani().doubleValue();
        LocalDate dogumTarihi = tazminatRapor.getTarihBilgileri().getKazaliDogumTarihi();

        LocalDate sonAktifCalismaTarihi = helper.getAktifCalismaTarih(dogumTarihi);//getAktifCalismaTarih(tazminatRapor.getTarihBilgileri().getKazaliDogumTarihi());
        LocalDate bakiyeTarih = sonAktifCalismaTarihi.plusYears(9);
        Integer bakiyeOmruArasiGun = helper.getIkiTarihArasindakiGun(sonAktifCalismaTarihi, bakiyeTarih); //aradakiGun(sonAktifCalismaTarihi, bakiyeTarih);
        
        PasifDevreHesabi pasifDevreHesabi = new PasifDevreHesabi();
        Double pasifGelir = 0.;
        
        pasifGelir = (asgariUcret.getAsgariUcretMiktar()/30*kusurOrani/100*maluliyet/100)*bakiyeOmruArasiGun;

        pasifDevreHesabi.setBugunkuZarar(pasifGelir);
        pasifDevreHesabi.setBakiyeOmur(sonAktifCalismaTarihi + " " + bakiyeTarih + " (" + bakiyeOmruArasiGun + " gün)");

        return pasifDevreHesabi; 
    }
}
