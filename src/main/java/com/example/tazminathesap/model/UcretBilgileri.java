package com.example.tazminathesap.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class UcretBilgileri extends BaseEntity {

	private Double gunlukCiplakYevmiye;
	private Double gunlukIkramiye;
	private Double gunlukServis;
	private Double gunlukYemek;
	private Double gunlukYakacak;
	private Double gunlukDigerHaklar;
	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "tazminat_id", nullable = true)
	private TazminatRapor tazminatRapor;
	

	public UcretBilgileri(Double gunlukCiplakYevmiye, Double gunlukIkramiye, Double gunlukServis, Double gunlukYemek, Double gunlukYakacak, Double gunlukDigerHaklar) {
		this.gunlukCiplakYevmiye = gunlukCiplakYevmiye;
		this.gunlukIkramiye = gunlukIkramiye;
		this.gunlukServis = gunlukServis;
		this.gunlukYemek = gunlukYemek;
		this.gunlukYakacak = gunlukYakacak;
		this.gunlukDigerHaklar = gunlukDigerHaklar;
	}

	public Double getYevmiye(){
		return this.gunlukCiplakYevmiye + this.gunlukIkramiye + this.gunlukServis + this.gunlukYemek + this.gunlukYakacak + this.gunlukDigerHaklar;
	}

}
