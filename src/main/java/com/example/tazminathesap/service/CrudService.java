package com.example.tazminathesap.service;

import java.util.Set;

import com.example.tazminathesap.model.BaseEntity;

public interface CrudService<T extends BaseEntity> {

	Set<T> findAll();
	
	T findById(Long id);
	
	T save(T object);
	
	void delete (T object);
	
	void deleteById(Long id);
}
