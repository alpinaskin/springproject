package com.example.tazminathesap.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class TarihBilgileri extends BaseEntity {

	private LocalDate raporTarihi;
	private LocalDate davaTarihi;
	private LocalDate kazaliDogumTarihi;
	private LocalDate kazaTarihi;
	private LocalDate ucretTarihi;
	private LocalDate istirahatBitisTarihi;
	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "tazminat_id", nullable = true)
	private TazminatRapor tazminatRapor;
	
	public TarihBilgileri(LocalDate raporTarihi, LocalDate davaTarihi, LocalDate kazaliDogumTarihi,
			LocalDate kazaTarihi, LocalDate ucretTarihi, LocalDate istirahatBitisTarihi) {
		super();
		this.raporTarihi = raporTarihi;
		this.davaTarihi = davaTarihi;
		this.kazaliDogumTarihi = kazaliDogumTarihi;
		this.kazaTarihi = kazaTarihi;
		this.ucretTarihi = ucretTarihi;
		this.istirahatBitisTarihi = istirahatBitisTarihi;
	}
	
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
		return ucretTarihi;
	}
	public void setUcretTarihi(LocalDate ucretTarihi) {
		this.ucretTarihi = ucretTarihi;
	}
	public LocalDate getIstirahatBitisTarihi() {
		return istirahatBitisTarihi;
	}
	public void setIstirahatBitisTarihi(LocalDate istirahatBitisTarihi) {
		this.istirahatBitisTarihi = istirahatBitisTarihi;
	}

	public TazminatRapor getTazminatRapor() {
		return tazminatRapor;
	}

	public void setTazminatRapor(TazminatRapor tazminatRapor) {
		this.tazminatRapor = tazminatRapor;
	}

	@Override
	public String toString() {
		return "TarihBilgileri [raporTarihi=" + raporTarihi + ", davaTarihi=" + davaTarihi + ", kazaliDogumTarihi="
				+ kazaliDogumTarihi + ", kazaTarihi=" + kazaTarihi + ", ucretTarihi=" + ucretTarihi
				+ ", istirahatBitisTarihi=" + istirahatBitisTarihi + "]";
	}
}
