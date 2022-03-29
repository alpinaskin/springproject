package com.example.tazminathesap.service;

import java.util.List;
import java.util.Optional;

import com.example.tazminathesap.model.BaseEntity;

public interface CrudService<T extends BaseEntity> {

	List<T> findAll();
	
	Optional<T> findById(Long id);
	
	T save(T object);
	
	T update(T object, Long id);
	
	void delete (T object);
	
	void deleteById(Long id);
}
