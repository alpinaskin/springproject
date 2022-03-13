package com.example.tazminathesap.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsgariUcretRepository extends JpaRepository<AsgariUcret, Long> {
    
    @Query(value = "select a from AsgariUcret a where a.bitisTarih <= :bitisTarih and a.baslangicTarih >= :baslangicTarih")
    List<AsgariUcret> findAllAsgariUcretBetweenDates(@Param("baslangicTarih") LocalDate baslangicTarih, @Param("bitisTarih") LocalDate bitisTarih);

    @Query(value ="select a from AsgariUcret a where a.bitisTarih >= :tarih and a.baslangicTarih <= :tarih")
    AsgariUcret findAsgariUcretByDate(@Param("tarih") LocalDate tarih);
}
