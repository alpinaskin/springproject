package com.example.tazminathesap.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class IstirahatSonrasiZarari extends BaseEntity{
    private Double tazminatMiktar;
    private String aciklama;
    @JsonBackReference
    @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name= "gecmisdevrehesabi_id", nullable = true)
    private GecmisDevreHesabi gecmisDevreHesabi;


}
