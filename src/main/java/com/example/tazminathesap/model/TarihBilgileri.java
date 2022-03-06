package com.example.tazminathesap.model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class TarihBilgileri extends BaseEntity {

	private LocalDate raporTarihi;
	private LocalDate davaTarihi;
	private LocalDate kazaliDogumTarihi;
	private LocalDate kazaTarihi;
	private LocalDate UcretTarihi;
	private LocalDate istirahatBitisTarihi;
	
	public TarihBilgileri() {
		super();
	}
	
	public LocalDate getRaporTarihi() {
		return raporTarihi;
	}
	public void setRaporTarihi(LocalDate raporTarihi) {
		this.raporTarihi = raporTarihi;
	}
	public LocalDate getDavaTarihi() {
		return davaTarihi;
	}
	public void setDavaTarihi(LocalDate davaTarihi) {
		this.davaTarihi = davaTarihi;
	}
	public LocalDate getKazaliDogumTarihi() {
		return kazaliDogumTarihi;
	}
	public void setKazaliDogumTarihi(LocalDate kazaliDogumTarihi) {
		this.kazaliDogumTarihi = kazaliDogumTarihi;
	}
	public LocalDate getKazaTarihi() {
		return kazaTarihi;
	}
	public void setKazaTarihi(LocalDate kazaTarihi) {
		this.kazaTarihi = kazaTarihi;
	}
	public LocalDate getUcretTarihi() {
		return UcretTarihi;
	}
	public void setUcretTarihi(LocalDate ucretTarihi) {
		UcretTarihi = ucretTarihi;
	}
	public LocalDate getIstirahatBitisTarihi() {
		return istirahatBitisTarihi;
	}
	public void setIstirahatBitisTarihi(LocalDate istirahatBitisTarihi) {
		this.istirahatBitisTarihi = istirahatBitisTarihi;
	}
}
