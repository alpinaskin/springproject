package com.example.tazminathesap.model;

import javax.persistence.Entity;

@Entity
public class RaporBilgileri extends BaseEntity {
	
	private String davaliAdi;
	private String davaciAdi;
	private String davaciVekili;
	private String bilirkisi;
	private String raporunDuzenlenecegiMakam;
	private String esasNo;
	
	
	public RaporBilgileri(String davaliAdi, String davaciAdi, String davaciVekili, String bilirkisi,
			String raporunDuzenlenecegiMakam, String esasNo) {
		super();
		this.davaliAdi = davaliAdi;
		this.davaciAdi = davaciAdi;
		this.davaciVekili = davaciVekili;
		this.bilirkisi = bilirkisi;
		this.raporunDuzenlenecegiMakam = raporunDuzenlenecegiMakam;
		this.esasNo = esasNo;
	}
	
	public String getDavaliAdi() {
		return davaliAdi;
	}
	public void setDavaliAdi(String davaliAdi) {
		this.davaliAdi = davaliAdi;
	}
	public String getDavaciAdi() {
		return davaciAdi;
	}
	public void setDavaciAdi(String davaciAdi) {
		this.davaciAdi = davaciAdi;
	}
	public String getDavaciVekili() {
		return davaciVekili;
	}
	public void setDavaciVekili(String davaciVekili) {
		this.davaciVekili = davaciVekili;
	}
	public String getBilirkisi() {
		return bilirkisi;
	}
	public void setBilirkisi(String bilirkisi) {
		this.bilirkisi = bilirkisi;
	}
	public String getRaporunDuzenlenecegiMakam() {
		return raporunDuzenlenecegiMakam;
	}
	public void setRaporunDuzenlenecegiMakam(String raporunDuzenlenecegiMakam) {
		this.raporunDuzenlenecegiMakam = raporunDuzenlenecegiMakam;
	}
	public String getEsasNo() {
		return esasNo;
	}
	public void setEsasNo(String esasNo) {
		this.esasNo = esasNo;
	}
}
