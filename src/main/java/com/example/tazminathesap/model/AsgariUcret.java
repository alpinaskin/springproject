package com.example.tazminathesap.model;

import java.time.LocalDate;
import javax.persistence.Entity;

@Entity
public class AsgariUcret extends BaseEntity { 
	
	private LocalDate baslangicTarih;
	private LocalDate bitisTarih;
	private Double asgariUcretMiktar;

	public AsgariUcret(LocalDate baslangicTarih, LocalDate bitisTarih, Double asgariUcretMiktar) {
		this.baslangicTarih = baslangicTarih;
		this.bitisTarih = bitisTarih;
		this.asgariUcretMiktar = asgariUcretMiktar;
	}

	public AsgariUcret() {
	}
	

	public LocalDate getBaslangicTarih() {
		return this.baslangicTarih;
	}

	public void setBaslangicTarih(LocalDate baslangicTarih) {
		this.baslangicTarih = baslangicTarih;
	}

	public LocalDate getBitisTarih() {
		return this.bitisTarih;
	}

	public void setBitisTarih(LocalDate bitisTarih) {
		this.bitisTarih = bitisTarih;
	}

	public Double getAsgariUcretMiktar() {
		return this.asgariUcretMiktar;
	}

	public void setAsgariUcretMiktar(Double asgariUcretMiktar) {
		this.asgariUcretMiktar = asgariUcretMiktar;
	}

	@Override
	public String toString() {
		return "{" +
			" baslangicTarih='" + getBaslangicTarih() + "'" +
			", bitisTarih='" + getBitisTarih() + "'" +
			", asgariUcretMiktar='" + getAsgariUcretMiktar() + "'" +
			"}";
	}
}