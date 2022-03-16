package com.example.tazminathesap.model;

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
	private Double asgariUcretMiktar;

}