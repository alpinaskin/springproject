package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class EkBilgiler extends BaseEntity {

	private Integer davaliKusurOrani; //0-100
	private Integer maluliyetOrani; // 0-100
	private Double sgkAyligiPesinDegeri;
	private Double geciciIsGoremezlikTutari;
	private Double maddiTazminatIstek;
	private Boolean kazaliCinsiyet; //Erkek 1, Kadin 0
	private Integer kazalininCocukSayisi;
	private Boolean kazalininMedeniHali; //Evli 1, Bekar 0
	private Boolean kazalininEsiCalisiyor; // Evet 1, Hayir 0
	
	public EkBilgiler(Integer davaliKusurOrani, Integer maluliyetOrani, Double sgkAyligiPesinDegeri,
			Double geciciIsGoremezlikTutari, Double maddiTazminatIstek, Boolean kazaliCinsiyet,
			Integer kazalininCocukSayisi, Boolean kazalininMedeniHali, Boolean kazalininEsiCalisiyor) {
		super();
		this.davaliKusurOrani = davaliKusurOrani;
		this.maluliyetOrani = maluliyetOrani;
		this.sgkAyligiPesinDegeri = sgkAyligiPesinDegeri;
		this.geciciIsGoremezlikTutari = geciciIsGoremezlikTutari;
		this.maddiTazminatIstek = maddiTazminatIstek;
		this.kazaliCinsiyet = kazaliCinsiyet;
		this.kazalininCocukSayisi = kazalininCocukSayisi;
		this.kazalininMedeniHali = kazalininMedeniHali;
		this.kazalininEsiCalisiyor = kazalininEsiCalisiyor;
	}
	public Integer getDavaliKusurOrani() {
		return davaliKusurOrani;
	}
	public void setDavaliKusurOrani(Integer davaliKusurOrani) {
		this.davaliKusurOrani = davaliKusurOrani;
	}
	public Integer getMaluliyetOrani() {
		return maluliyetOrani;
	}
	public void setMaluliyetOrani(Integer maluliyetOrani) {
		this.maluliyetOrani = maluliyetOrani;
	}
	public Double getSgkAyligiPesinDegeri() {
		return sgkAyligiPesinDegeri;
	}
	public void setSgkAyligiPesinDegeri(Double sgkAyligiPesinDegeri) {
		this.sgkAyligiPesinDegeri = sgkAyligiPesinDegeri;
	}
	public Double getGeciciIsGoremezlikTutari() {
		return geciciIsGoremezlikTutari;
	}
	public void setGeciciIsGoremezlikTutari(Double geciciIsGoremezlikTutari) {
		this.geciciIsGoremezlikTutari = geciciIsGoremezlikTutari;
	}
	public Double getMaddiTazminatIstek() {
		return maddiTazminatIstek;
	}
	public void setMaddiTazminatIstek(Double maddiTazminatIstek) {
		this.maddiTazminatIstek = maddiTazminatIstek;
	}
	public Boolean getKazaliCinsiyet() {
		return kazaliCinsiyet;
	}
	public void setKazaliCinsiyet(Boolean kazaliCinsiyet) {
		this.kazaliCinsiyet = kazaliCinsiyet;
	}
	public Integer getKazalininCocukSayisi() {
		return kazalininCocukSayisi;
	}
	public void setKazalininCocukSayisi(Integer kazalininCocukSayisi) {
		this.kazalininCocukSayisi = kazalininCocukSayisi;
	}
	public Boolean getKazalininMedeniHali() {
		return kazalininMedeniHali;
	}
	public void setKazalininMedeniHali(Boolean kazalininMedeniHali) {
		this.kazalininMedeniHali = kazalininMedeniHali;
	}
	public Boolean getKazalininEsiCalisiyor() {
		return kazalininEsiCalisiyor;
	}
	public void setKazalininEsiCalisiyor(Boolean kazalininEsiCalisiyor) {
		this.kazalininEsiCalisiyor = kazalininEsiCalisiyor;
	}
	
	
}
