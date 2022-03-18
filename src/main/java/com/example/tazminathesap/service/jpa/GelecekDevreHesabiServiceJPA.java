package com.example.tazminathesap.service.jpa;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import com.example.tazminathesap.model.GelecekDevreHesabi;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.model.ZararDonemi;
import com.example.tazminathesap.repository.GelecekDevreHesabiRepository;
import com.example.tazminathesap.service.GelecekDevreHesabiService;
import com.example.tazminathesap.util.RaporFormatHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GelecekDevreHesabiServiceJPA extends AbstractJpaService<GelecekDevreHesabi, GelecekDevreHesabiRepository> implements GelecekDevreHesabiService {

    @Autowired
    RaporFormatHelper helper;

    public GelecekDevreHesabiServiceJPA(GelecekDevreHesabiRepository repository) {
        super(repository);
    }
    
    @Override
    public GelecekDevreHesabi saveGelecekDevreHesabi(TazminatRapor tazminatRapor, AsgariUcret asgariUcret)
    {   
        LocalDate dogumTarihi = tazminatRapor.getTarihBilgileri().getKazaliDogumTarihi();
        LocalDate raporTarihi = tazminatRapor.getTarihBilgileri().getRaporTarihi();
        LocalDate sonAktifCalismaTarihi = helper.getAktifCalismaTarih(dogumTarihi);
        Double yevmiye = tazminatRapor.getUcretBilgileri().getGunlukCiplakYevmiye();
        Double asgariUcretMiktar = asgariUcret.getAsgariUcretMiktar();
        Double zararToplam = 0.;
        GelecekDevreHesabi gelecekDevreHesabi = new GelecekDevreHesabi();
 

        gelecekDevreHesabi.setZararDonemleri(zararDonemleriHesapla(helper.getSonrakiYilinBasi(raporTarihi), sonAktifCalismaTarihi, asgariUcretMiktar, yevmiye));        
        zararToplam = gelecekDevreHesabi.getZararDonemleri().stream().mapToDouble((e) -> e.getDonemZarar()).sum();
        gelecekDevreHesabi.setAktifDevreToplami(zararToplam);

        return gelecekDevreHesabi;
    }

    private List<ZararDonemi> zararDonemleriHesapla(LocalDate baslangic, LocalDate bitis, Double asgariUcretMiktar, Double yevmiye) {
        List<ZararDonemi> zararDonemleriTemp = new ArrayList<>();
        ZararDonemi zararDonemi;

        while(helper.tarihOnce(baslangic, bitis)){

            if(!(baslangic.getYear() == bitis.getYear()))
            {
                zararDonemi = new ZararDonemi();
                zararDonemi.setDonemBaslangicTarihi(baslangic);
                zararDonemi.setDonemBitisTarihi(baslangic.with(TemporalAdjusters.lastDayOfYear()));
                zararDonemi.setDonemZarar(asgariUcretMiktar*helper.getIkiTarihArasindakiGun(baslangic, baslangic.with(TemporalAdjusters.lastDayOfYear()))*(yevmiye/asgariUcretMiktar));
                zararDonemleriTemp.add(zararDonemi);
            }else
            {
                zararDonemi = new ZararDonemi();
                zararDonemi.setDonemBaslangicTarihi(baslangic);
                zararDonemi.setDonemBitisTarihi(bitis);
                zararDonemi.setDonemZarar(asgariUcretMiktar*helper.getIkiTarihArasindakiGun(baslangic, bitis)*(yevmiye/asgariUcretMiktar));
                zararDonemleriTemp.add(zararDonemi);
            }

           baslangic = baslangic.plusYears(1);
        }

        return zararDonemleriTemp;
    }
}
