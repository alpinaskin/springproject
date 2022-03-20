package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.example.tazminathesap.exception.ObjectNotCreatedException;
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

    private final BigDecimal AYDAKI_GUN = new BigDecimal("30");

    @Autowired
    RaporFormatHelper helper;

    public PasifDevreHesabiServiceJPA(PasifDevreHesabiRepository repository) {
        super(repository);
    }

    public PasifDevreHesabi createPasifDevreHesabiByTazminatIdAndAsgariUcret(TazminatRapor tazminatRapor, AsgariUcret asgariUcret){

        Optional.of(tazminatRapor).orElseThrow(() -> new ObjectNotCreatedException("Tazminat Rapor null oluşturulamadı!"));        
        
        Double kusurOrani = tazminatRapor.getEkBilgiler().getDavaliKusurOrani();
        Double maluliyet = tazminatRapor.getEkBilgiler().getDavaliKusurOrani();
        LocalDate dogumTarihi = tazminatRapor.getTarihBilgileri().getKazaliDogumTarihi();

        LocalDate sonAktifCalismaTarihi = helper.getAktifCalismaTarih(dogumTarihi);
        LocalDate bakiyeTarih = sonAktifCalismaTarihi.plusYears(9);
        Integer bakiyeOmruArasiGun = helper.getIkiTarihArasindakiGun(sonAktifCalismaTarihi, bakiyeTarih);

        BigDecimal pasifGelir = asgariUcret.getAsgariUcretMiktar()
            .divide(AYDAKI_GUN)
            .multiply(new BigDecimal(kusurOrani*maluliyet*bakiyeOmruArasiGun)); 
    
        return Optional.of(new PasifDevreHesabi(sonAktifCalismaTarihi, bakiyeTarih, null, pasifGelir))
            .orElseThrow(() -> new ObjectNotCreatedException("Pasif Devre Hesabı oluşturulamadı"));
    }
}
