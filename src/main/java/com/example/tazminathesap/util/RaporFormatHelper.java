package com.example.tazminathesap.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.example.tazminathesap.model.TazminatRapor;

import org.springframework.stereotype.Component;

@Component
public class RaporFormatHelper {
    
    private Long days = 0L;

    public void setDays(LocalDate ilkTarih, LocalDate sonTarih)
    {
        this.days = ChronoUnit.DAYS.between(ilkTarih, sonTarih);
    }
    
    public Long getDays(){
        return this.days;
    }
    
    public LocalDate getAktifCalismaTarih(LocalDate dogumTarihi){
        return dogumTarihi.plusYears(60);
    }
    
    public Integer getIkiTarihArasindakiGun(LocalDate ilkTarih, LocalDate sonTarih){

        setDays(ilkTarih, sonTarih);
        
        return getDays().intValue();
    }

    public LocalDate getSonrakiYilinBasi(LocalDate raporTarihi){
        return LocalDate.of(raporTarihi.plusDays(1).getYear(), 1, 1);
    }

    public boolean tarihOnce(LocalDate ilkTarih, LocalDate sonTarih){
        return ilkTarih.isBefore(sonTarih);
    }

    public String getIkiTarihArasi(TazminatRapor tazminatRapor)
    {
        LocalDate kazaTarihi = tazminatRapor.getTarihBilgileri().getKazaTarihi();
        LocalDate raporTarihiSonu = LocalDate.of(tazminatRapor.getTarihBilgileri().getRaporTarihi().getYear(), 12, 31);
        
        Period period = Period.between(kazaTarihi, raporTarihiSonu);
        String ikiTarihArasiFormat = "Yıl: " + period.getYears() + " Ay: " + period.getMonths() + " Gün: " + period.getDays();

        return ikiTarihArasiFormat;
    }

    public LocalDate getYilSonu(LocalDate date){
        return LocalDate.of(date.getYear(),12,31);
    }
}
