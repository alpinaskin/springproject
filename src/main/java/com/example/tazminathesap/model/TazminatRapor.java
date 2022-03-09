package com.example.tazminathesap.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class TazminatRapor extends BaseEntity{
	@JsonManagedReference
	@OneToOne(mappedBy="tazminatRapor", cascade = CascadeType.ALL, orphanRemoval = true)
	private RaporBilgileri raporBilgileri;
	@JsonManagedReference
	@OneToOne(mappedBy="tazminatRapor", cascade = CascadeType.ALL, orphanRemoval = true)
	private UcretBilgileri ucretBilgileri;
	@JsonManagedReference
	@OneToOne(mappedBy="tazminatRapor", cascade = CascadeType.ALL, orphanRemoval = true)
	private TarihBilgileri tarihBilgileri;
	@JsonManagedReference
	@OneToOne(mappedBy="tazminatRapor", cascade = CascadeType.ALL, orphanRemoval = true)
	private EkBilgiler ekBilgiler;
	
	public TazminatRapor(RaporBilgileri raporBilgileri, UcretBilgileri ucretBilgileri, TarihBilgileri tarihBilgileri,
			EkBilgiler ekBilgiler) {
		super();
		this.raporBilgileri = raporBilgileri;
		this.ucretBilgileri = ucretBilgileri;
		this.tarihBilgileri = tarihBilgileri;
		this.ekBilgiler = ekBilgiler;
	}
	public TazminatRapor() {
		super();
	}
	public RaporBilgileri getRaporBilgileri() {
		return raporBilgileri;
	}
	public void setRaporBilgileri(RaporBilgileri raporBilgileri) {
		this.raporBilgileri = raporBilgileri;
	}
	public UcretBilgileri getUcretBilgileri() {
		return ucretBilgileri;
	}
	public void setUcretBilgileri(UcretBilgileri ucretBilgileri) {
		this.ucretBilgileri = ucretBilgileri;
	}
	public TarihBilgileri getTarihBilgileri() {
		return tarihBilgileri;
	}
	public void setTarihBilgileri(TarihBilgileri tarihBilgileri) {
		this.tarihBilgileri = tarihBilgileri;
	}
	public EkBilgiler getEkBilgiler() {
		return ekBilgiler;
	}
	public void setEkBilgiler(EkBilgiler ekBilgiler) {
		this.ekBilgiler = ekBilgiler;
	}
	@Override
	public String toString() {
		return "TazminatRapor [raporBilgileri=" + raporBilgileri + ", ucretBilgileri=" + ucretBilgileri
				+ ", tarihBilgileri=" + tarihBilgileri + ", ekBilgiler=" + ekBilgiler + "]";
	}
	
}
