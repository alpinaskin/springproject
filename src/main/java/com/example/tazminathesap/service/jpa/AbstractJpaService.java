package com.example.tazminathesap.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.tazminathesap.exception.NotFoundException;
import com.example.tazminathesap.model.BaseEntity;
import com.example.tazminathesap.service.CrudService;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractJpaService<T extends BaseEntity, R extends JpaRepository<T, Long>> implements CrudService<T> {

	protected R repository;
	
	public AbstractJpaService(R repository) {
		this.repository = repository;
	}
	
	public List<T> findAll() {
		return new ArrayList<>(repository.findAll());
	}
	
	public Optional<T> findById(Long id) {
		return repository.findById(id);
	}

	public T save(T object)
	{
		return repository.save(object);
	}
	
	public T update(T object, Long id) {
		T objectToBeUpdated = this.findById(id).orElseThrow(() -> new NotFoundException(id + " id bulunamadı!"));
		object.setId(objectToBeUpdated.getId());
		return this.save(object);
	}

	public void delete(T object) {
		repository.delete(object);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
