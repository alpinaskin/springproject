package com.example.tazminathesap.repository;

import java.util.Date;
import java.util.List;

import com.example.tazminathesap.model.AsgariUcret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsgariUcretRepository extends JpaRepository<AsgariUcret, Long> {
    
    @Query(value = "select a from AsgariUcret a where a.bitisTarih <= :baslangicTarih and a.baslangicTarih >= :bitisTarih")
    List<AsgariUcret> findAllAsgariUcretBetweenDates(@Param("baslangicTarih") Date baslangicTarih, @Param("bitisTarih") Date bitisTarih);
}
