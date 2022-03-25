package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.tazminathesap.exception.EntityNotCreatedException;
import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.PasifDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.repository.PasifDevreHesabiRepository;
import com.example.tazminathesap.repository.TrhTabloRepository;
import com.example.tazminathesap.service.PasifDevreHesabiService;
import com.example.tazminathesap.util.RaporFormatHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasifDevreHesabiServiceJPA extends AbstractJpaService<PasifDevreHesabi, PasifDevreHesabiRepository> implements PasifDevreHesabiService {

    private final TrhTabloRepository tabloRepository;

    @Autowired
    RaporFormatHelper helper;

    public PasifDevreHesabiServiceJPA(PasifDevreHesabiRepository repository, TrhTabloRepository trhTabloRepository) {
        super(repository);
        this.tabloRepository = trhTabloRepository;
    }

    public PasifDevreHesabi createPasifDevreHesabiByTazminatIdAndAsgariUcret(TazminatRapor tazminatRapor, AsgariUcret asgariUcret){

        Optional.of(tazminatRapor).orElseThrow(() -> new EntityNotCreatedException("Tazminat Rapor null oluşturulamadı!"));        
        
        Double kusurOrani = tazminatRapor.getEkBilgiler().getDavaliKusurOrani();
        Double maluliyet = tazminatRapor.getEkBilgiler().getDavaliKusurOrani();
        LocalDate dogumTarihi = tazminatRapor.getTarihBilgileri().getKazaliDogumTarihi();
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();

        LocalDate sonAktifCalismaTarihi = helper.getAktifCalismaTarih(dogumTarihi);
        LocalDate bakiyeTarih = kazaTarihi.plusYears(tabloRepository.getTrhTabloByDefaultVar(tazminatRapor.getTarihBilgileri().getYas(kazaTarihi)).getErkek().longValue());
        Integer bakiyeOmruArasiGun = helper.getIkiTarihArasindakiGun(sonAktifCalismaTarihi, bakiyeTarih);

        BigDecimal pasifGelir = new BigDecimal(asgariUcret.getGunlukAsgariUcret()*kusurOrani*maluliyet*bakiyeOmruArasiGun*(tazminatRapor.getUcretBilgileri().getYevmiye()/asgariUcret.getGunlukAsgariUcret()));
    
        return Optional.of(new PasifDevreHesabi(sonAktifCalismaTarihi, bakiyeTarih, null, pasifGelir))
            .orElseThrow(() -> new EntityNotCreatedException("Pasif Devre Hesabı oluşturulamadı"));
    }
}
