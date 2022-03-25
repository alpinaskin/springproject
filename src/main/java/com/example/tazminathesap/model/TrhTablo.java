package com.example.tazminathesap.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TrhTablo extends BaseEntity {
    private Integer yas;
    private Double erkek;
    private Double kadin;
}
