package com.example.tazminathesap.model;

import java.time.LocalDate;
import java.time.Period;

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


	public TarihBilgileri(LocalDate raporTarihi, LocalDate davaTarihi, LocalDate kazaliDogumTarihi, LocalDate kazaTarihi, LocalDate ucretTarihi, LocalDate istirahatBitisTarihi) {
		this.raporTarihi = raporTarihi;
		this.davaTarihi = davaTarihi;
		this.kazaliDogumTarihi = kazaliDogumTarihi;
		this.kazaTarihi = kazaTarihi;
		this.ucretTarihi = ucretTarihi;
		this.istirahatBitisTarihi = istirahatBitisTarihi;
	}

	
	public Integer getYas(LocalDate currentDate){
		if((this.kazaliDogumTarihi != null) && (currentDate != null))
			return Period.between(this.kazaliDogumTarihi, currentDate).getYears();
		else
			return null;
	}
}
