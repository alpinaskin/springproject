package com.example.tazminathesap.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsgariUcretRepository extends JpaRepository<AsgariUcret, Long> {
    
    @Query(value = "select a from AsgariUcret a where not a.baslangicTarih >= :bitisTarih and not a.bitisTarih <= :baslangicTarih")
    List<AsgariUcret> findAllAsgariUcretBetweenDates(@Param("baslangicTarih") LocalDate baslangicTarih, @Param("bitisTarih") LocalDate bitisTarih);

    @Query(value ="select a from AsgariUcret a where a.bitisTarih >= :tarih and a.baslangicTarih <= :tarih")
    AsgariUcret findAsgariUcretByDate(@Param("tarih") LocalDate tarih);

    @Query(nativeQuery = true,
    value="SELECT BASLANGIC_TARIH AS DONEM_BASI,CASE WHEN BITIS_TARIH < FORMATDATETIME(BASLANGIC_TARIH,'Y-12-31') THEN BITIS_TARIH ELSE FORMATDATETIME(BASLANGIC_TARIH, 'Y-12-31') END AS DONEM_SONU,ASGARI_UCRET_MIKTAR/30*DATEDIFF(dd, BASLANGIC_TARIH, BITIS_TARIH) AS ASGARI_UCRET_DONEM FROM ASGARI_UCRET a, TARIH_BILGILERI t WHERE t.ID = :idNo AND a.BASLANGIC_TARIH <= (SELECT RAPOR_TARIHI FROM TARIH_BILGILERI WHERE ID = :idNo)  AND a.BITIS_TARIH >= (SELECT ISTIRAHAT_BITIS_TARIHI FROM TARIH_BILGILERI WHERE ID = :idNo)")
    List<Object[]> findByDefault(@Param("idNo") Long id);
    // SELECT 
    // BASLANGIC_TARIH AS DONEM_BASI,

    // CASE 
    // WHEN BITIS_TARIH < FORMATDATETIME(BASLANGIC_TARIH,'Y-12-31') THEN BITIS_TARIH
    // ELSE FORMATDATETIME(BASLANGIC_TARIH, 'Y-12-31')
    // END AS DONEM_SONU,

    // ASGARI_UCRET_MIKTAR/30*DATEDIFF(dd, BASLANGIC_TARIH, BITIS_TARIH) AS ASGARI_UCRET_DONEM

    // FROM ASGARI_UCRET a, TARIH_BILGILERI t WHERE a.BASLANGIC_TARIH <= (SELECT RAPOR_TARIHI FROM TARIH_BILGILERI WHERE ID = 1)  AND a.BITIS_TARIH >= (SELECT ISTIRAHAT_BITIS_TARIHI FROM TARIH_BILGILERI WHERE ID = 1)  AND t.ID = 1;


}
