package com.example.tazminathesap.repository;
import com.example.tazminathesap.model.TrhTablo;
import com.example.tazminathesap.model.TrhTabloView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrhTabloRepository extends JpaRepository<TrhTablo,Long>{

    @Query(value="Select t From TrhTablo t where t.yas=?1")
    TrhTabloView getTrhTabloByDefaultVar(Integer i);
}
