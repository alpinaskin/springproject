package com.example.tazminathesap.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class GecmisDevreHesabi extends BaseEntity{
    private String kazaTarihiRaporYiliSonu;
    private BigDecimal istirahatliDonemZarari;
    @JsonManagedReference
    @OneToMany(mappedBy = "gecmisDevreHesabi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IstirahatSonrasiZarari> istirahatSonrasiZarari = new ArrayList<>();
    private BigDecimal gecmisDevreZarari;

}
