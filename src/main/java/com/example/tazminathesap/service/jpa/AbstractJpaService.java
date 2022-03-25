package com.example.tazminathesap.service.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tazminathesap.model.BaseEntity;
import com.example.tazminathesap.service.CrudService;

public abstract class AbstractJpaService<T extends BaseEntity, R extends JpaRepository<T, Long>> implements CrudService<T> {

	protected R repository;
	
	public AbstractJpaService(R repository) {
		this.repository = repository;
	}
	
	public List<T> findAll() {
		return new ArrayList<>(repository.findAll());
	}
	
	public T findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public T save(T object)
	{
		return repository.save(object);
	}
	
	public void delete(T object) {
		repository.delete(object);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
