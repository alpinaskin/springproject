package com.example.tazminathesap.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
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
public class AsgariUcret extends BaseEntity { 
	private LocalDate baslangicTarih;
	private LocalDate bitisTarih;
	private BigDecimal asgariUcretMiktar;

	public Double getGunlukAsgariUcret(){
		return asgariUcretMiktar.doubleValue()/30;
	}
}