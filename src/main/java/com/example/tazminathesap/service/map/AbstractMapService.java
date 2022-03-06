package com.example.tazminathesap.service.map;

import com.example.tazminathesap.model.BaseEntity;
import com.example.tazminathesap.service.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AbstractMapService <T extends BaseEntity> implements CrudService<T> {
	
	protected final Map<Long, T> map = new HashMap<>();

	@Override
	public Set<T> findAll() {
		return new HashSet<>(map.values());
	}
	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return map.get(id);
	}
	@Override
	public T save(T object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
}
