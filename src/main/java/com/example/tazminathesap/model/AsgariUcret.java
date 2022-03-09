package com.example.tazminathesap.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class AsgariUcret extends BaseEntity { 
	
	private Date baslangicTarih;
	private Date bitisTarih;
	private Double asgariUcretMiktar;
	
}