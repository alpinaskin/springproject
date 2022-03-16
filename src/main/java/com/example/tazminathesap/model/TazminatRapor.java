package com.example.tazminathesap.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
}
