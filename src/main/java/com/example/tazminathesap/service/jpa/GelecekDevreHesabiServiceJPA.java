package com.example.tazminathesap.service.jpa;

import java.math.BigDecimal;
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
        BigDecimal asgariUcretMiktar = asgariUcret.getAsgariUcretMiktar();
        GelecekDevreHesabi gelecekDevreHesabi = new GelecekDevreHesabi();
 

        gelecekDevreHesabi.setZararDonemleri(zararDonemleriHesapla(helper.getSonrakiYilinBasi(raporTarihi), sonAktifCalismaTarihi, asgariUcretMiktar, yevmiye));        
        BigDecimal zararToplam = gelecekDevreHesabi.getZararDonemleri().stream().map(e -> e.getDonemZarar()).reduce(BigDecimal.ZERO, BigDecimal::add);
        gelecekDevreHesabi.setAktifDevreToplami(zararToplam);

        return gelecekDevreHesabi;
    }

    private List<ZararDonemi> zararDonemleriHesapla(LocalDate baslangic, LocalDate bitis, BigDecimal asgariUcretMiktar, Double yevmiye) {
        List<ZararDonemi> zararDonemleriTemp = new ArrayList<>();
        ZararDonemi zararDonemi;

        while(helper.tarihOnce(baslangic, bitis)){

            if(!(baslangic.getYear() == bitis.getYear()))
            {
//asgariUcretMiktar*helper.getIkiTarihArasindakiGun(baslangic, baslangic.with(TemporalAdjusters.lastDayOfYear()))*(yevmiye/asgariUcretMiktar)
                
                BigDecimal netToplam = asgariUcretMiktar.multiply(new BigDecimal(yevmiye/asgariUcretMiktar.doubleValue()))
                    .multiply(new BigDecimal(helper.getIkiTarihArasindakiGun(baslangic, baslangic.with(TemporalAdjusters.lastDayOfYear()))));

                zararDonemi = new ZararDonemi();
                zararDonemi.setDonemBaslangicTarihi(baslangic);
                zararDonemi.setDonemBitisTarihi(baslangic.with(TemporalAdjusters.lastDayOfYear()));
                zararDonemi.setDonemZarar(netToplam);
                zararDonemleriTemp.add(zararDonemi);
            }else
            {
                BigDecimal netToplam = asgariUcretMiktar.multiply(new BigDecimal(yevmiye/asgariUcretMiktar.doubleValue()))
                .multiply(new BigDecimal(helper.getIkiTarihArasindakiGun(baslangic, bitis)));

                zararDonemi = new ZararDonemi();
                zararDonemi.setDonemBaslangicTarihi(baslangic);
                zararDonemi.setDonemBitisTarihi(bitis);
                zararDonemi.setDonemZarar(netToplam);
                zararDonemleriTemp.add(zararDonemi);
            }

           baslangic = baslangic.plusYears(1);
        }

        return zararDonemleriTemp;
    }
}
