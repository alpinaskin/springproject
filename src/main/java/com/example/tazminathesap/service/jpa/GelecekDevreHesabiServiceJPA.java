package com.example.tazminathesap.service.jpa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.model.ZararDonemi;
import com.example.tazminathesap.repository.GelecekDevreHesabiRepository;
import com.example.tazminathesap.service.GelecekDevreHesabiService;

import org.springframework.stereotype.Service;

@Service
public class GelecekDevreHesabiServiceJPA extends AbstractJpaService<GelecekDevreHesabi, GelecekDevreHesabiRepository> implements GelecekDevreHesabiService {

    public GelecekDevreHesabiServiceJPA(GelecekDevreHesabiRepository repository) {
        super(repository);
    }
    
    @Override
    public GelecekDevreHesabi saveGelecekDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret)
    {   
        LocalDate dogumTarihi = tazminatRapor.getTarihBilgileri().getKazaliDogumTarihi();
        LocalDate raporTarihi = tazminatRapor.getTarihBilgileri().getRaporTarihi();
        LocalDate sonAktifCalismaTarihi = getAktifCalismaTarih(dogumTarihi);
        Double yevmiye = tazminatRapor.getUcretBilgileri().getGunlukCiplakYevmiye();
        Double asgariUcretMiktar = asgariUcret.getAsgariUcretMiktar();
        Double zararToplam = 0.;
        //TODO: Gelecek devre raporu her yıl için hesapla
        GelecekDevreHesabi gelecekDevreHesabi = new GelecekDevreHesabi();
 

        gelecekDevreHesabi.setZararDonemleri(zararDonemleriHesapla(getLocalDateWithNextYear(raporTarihi), sonAktifCalismaTarihi, asgariUcretMiktar, yevmiye));        
        zararToplam = gelecekDevreHesabi.getZararDonemleri().stream().mapToDouble((e) -> e.getDonemZarar()).sum();
        gelecekDevreHesabi.setAktifDevreToplami(zararToplam);

        return gelecekDevreHesabi;
    }

    private List<ZararDonemi> zararDonemleriHesapla(LocalDate baslangic, LocalDate bitis, Double asgariUcretMiktar, Double yevmiye) {
        List<ZararDonemi> zararDonemleriTemp = new ArrayList<>();
        ZararDonemi zararDonemi;

        while(tarihOnce(baslangic, bitis)){
            //TODO: İşlemler...
            
            if(!(baslangic.getYear() == bitis.getYear()))
            {
                zararDonemi = new ZararDonemi();
                zararDonemi.setAciklama(baslangic+" "+ baslangic.with(TemporalAdjusters.lastDayOfYear()));
                zararDonemi.setDonemZarar(asgariUcretMiktar*aradakiGun(baslangic, baslangic.with(TemporalAdjusters.lastDayOfYear()))*(yevmiye/asgariUcretMiktar));
                zararDonemleriTemp.add(zararDonemi);
            }else
            {
                zararDonemi = new ZararDonemi();
                zararDonemi.setAciklama(baslangic+" "+ bitis);
                zararDonemi.setDonemZarar(asgariUcretMiktar*aradakiGun(baslangic, bitis)*(yevmiye/asgariUcretMiktar));
                zararDonemleriTemp.add(zararDonemi);
            }

           baslangic = baslangic.plusYears(1);
        }

        return zararDonemleriTemp;
    }

    private LocalDate getLocalDateWithNextYear(LocalDate raporTarihi){
        return LocalDate.of(raporTarihi.plusDays(1).getYear(), 1, 1);
    }

    private LocalDate getAktifCalismaTarih(LocalDate dogumTarihi){
        return dogumTarihi.plusYears(60);
    }

    private boolean tarihOnce(LocalDate ilkTarih, LocalDate sonTarih){
        return ilkTarih.isBefore(sonTarih);
    }
    private Integer aradakiGun(LocalDate ilkTarih, LocalDate sonTarih){

        Long days = ChronoUnit.DAYS.between(ilkTarih, sonTarih);
        return days.intValue();
    }
    
}
