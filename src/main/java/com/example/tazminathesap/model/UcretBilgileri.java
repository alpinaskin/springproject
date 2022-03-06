package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class UcretBilgileri extends BaseEntity {

	private Double gunlukCiplakYevmiye;
	private Double gunlukIkramiye;
	private Double gunlukServis;
	private Double gunlukYemek;
	private Double gunlukYakacak;
	private Double gunlukDigerHaklar;

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
	
	
	
}
