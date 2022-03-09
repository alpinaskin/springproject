package com.example.tazminathesap.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class AsgariUcret extends BaseEntity { 
	
	private Date baslangicTarih;
	private Date bitisTarih;
	private Double asgariUcretMiktar;

	public AsgariUcret(Date baslangicTarih, Date bitisTarih, Double asgariUcretMiktar) {
		this.baslangicTarih = baslangicTarih;
		this.bitisTarih = bitisTarih;
		this.asgariUcretMiktar = asgariUcretMiktar;
	}

	public AsgariUcret() {
	}
	

	public Date getBaslangicTarih() {
		return this.baslangicTarih;
	}

	public void setBaslangicTarih(Date baslangicTarih) {
		this.baslangicTarih = baslangicTarih;
	}

	public Date getBitisTarih() {
		return this.bitisTarih;
	}

	public void setBitisTarih(Date bitisTarih) {
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