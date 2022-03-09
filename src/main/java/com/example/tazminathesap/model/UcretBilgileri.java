package com.example.tazminathesap.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	public UcretBilgileri(Double gunlukCiplakYevmiye, Double gunlukIkramiye, Double gunlukServis, Double gunlukYemek,
			Double gunlukYakacak, Double gunlukDigerHaklar) {
		super();
		this.gunlukCiplakYevmiye = gunlukCiplakYevmiye;
		this.gunlukIkramiye = gunlukIkramiye;
		this.gunlukServis = gunlukServis;
		this.gunlukYemek = gunlukYemek;
		this.gunlukYakacak = gunlukYakacak;
		this.gunlukDigerHaklar = gunlukDigerHaklar;
	}
	
	public UcretBilgileri() {
		super();
	}
	
	public Double getGunlukCiplakYevmiye() {
		return gunlukCiplakYevmiye;
	}
	public void setGunlukCiplakYevmiye(Double gunlukCiplakYevmiye) {
		this.gunlukCiplakYevmiye = gunlukCiplakYevmiye;
	}
	public Double getGunlukIkramiye() {
		return gunlukIkramiye;
	}
	public void setGunlukIkramiye(Double gunlukIkramiye) {
		this.gunlukIkramiye = gunlukIkramiye;
	}
	public Double getGunlukServis() {
		return gunlukServis;
	}
	public void setGunlukServis(Double gunlukServis) {
		this.gunlukServis = gunlukServis;
	}
	public Double getGunlukYemek() {
		return gunlukYemek;
	}
	public void setGunlukYemek(Double gunlukYemek) {
		this.gunlukYemek = gunlukYemek;
	}
	public Double getGunlukYakacak() {
		return gunlukYakacak;
	}
	public void setGunlukYakacak(Double gunlukYakacak) {
		this.gunlukYakacak = gunlukYakacak;
	}
	public Double getGunlukDigerHaklar() {
		return gunlukDigerHaklar;
	}
	public void setGunlukDigerHaklar(Double gunlukDigerHaklar) {
		this.gunlukDigerHaklar = gunlukDigerHaklar;
	}

	public TazminatRapor getTazminatRapor() {
		return tazminatRapor;
	}

	public void setTazminatRapor(TazminatRapor tazminatRapor) {
		this.tazminatRapor = tazminatRapor;
	}

	@Override
	public String toString() {
		return "UcretBilgileri [gunlukCiplakYevmiye=" + gunlukCiplakYevmiye + ", gunlukIkramiye=" + gunlukIkramiye
				+ ", gunlukServis=" + gunlukServis + ", gunlukYemek=" + gunlukYemek + ", gunlukYakacak=" + gunlukYakacak
				+ ", gunlukDigerHaklar=" + gunlukDigerHaklar + "]";
	}
	
	
	
}
