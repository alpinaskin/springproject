package com.example.tazminathesap.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TazminatRapor extends BaseEntity{
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "raporBilgileri_id", referencedColumnName="id")
	private RaporBilgileri raporBilgileri;
	@OneToOne(cascade = CascadeType.ALL)
	private UcretBilgileri ucretBilgileri;
	@OneToOne(cascade = CascadeType.ALL)
	private TarihBilgileri tarihBilgileri;
	@OneToOne(cascade = CascadeType.ALL)
	private EkBilgiler ekBilgiler;
	
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
	
}
