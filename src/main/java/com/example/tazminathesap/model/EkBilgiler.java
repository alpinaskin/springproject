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
public class EkBilgiler extends BaseEntity {
	private Double davaliKusurOrani; //0-1 arası
	private Double maluliyetOrani; // 0-1 arası
	private Double sgkAyligiPesinDegeri;
	private Double geciciIsGoremezlikTutari;
	private Double maddiTazminatIstek;
	private Boolean kazaliCinsiyet; //Erkek 1, Kadin 0
	private Integer kazalininCocukSayisi;
	private Boolean kazalininMedeniHali; //Evli 1, Bekar 0
	private Boolean kazalininEsiCalisiyor; // Evet 1, Hayir 0
	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "tazminat_id", nullable = true)
	private TazminatRapor tazminatRapor;


	public EkBilgiler(Double davaliKusurOrani, Double maluliyetOrani, Double sgkAyligiPesinDegeri, Double geciciIsGoremezlikTutari, Double maddiTazminatIstek, Boolean kazaliCinsiyet, Integer kazalininCocukSayisi, Boolean kazalininMedeniHali, Boolean kazalininEsiCalisiyor) {
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

	
}
