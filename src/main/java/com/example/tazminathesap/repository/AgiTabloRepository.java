package com.example.tazminathesap.repository;

import com.example.tazminathesap.model.AgiTablo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgiTabloRepository extends JpaRepository<AgiTablo, Long>{
    
    AgiTablo findAgiTabloByYilAndCocukSayisiAndEsCalismaDurumu(Integer yil, Integer cocukSayisi, boolean esCalismaDurumu);
}
