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
public class RaporBilgileri extends BaseEntity {
	
	private String davaliAdi;
	private String davaciAdi;
	private String davaciVekili;
	private String bilirkisi;
	private String raporunDuzenlenecegiMakam;
	private String esasNo;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "tazminat_id", nullable = true)
	private TazminatRapor tazminatRapor;
	

	public RaporBilgileri(String davaliAdi, String davaciAdi, String davaciVekili, String bilirkisi, String raporunDuzenlenecegiMakam, String esasNo) {
		this.davaliAdi = davaliAdi;
		this.davaciAdi = davaciAdi;
		this.davaciVekili = davaciVekili;
		this.bilirkisi = bilirkisi;
		this.raporunDuzenlenecegiMakam = raporunDuzenlenecegiMakam;
		this.esasNo = esasNo;
	}

}
