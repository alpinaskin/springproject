package com.example.tazminathesap.controller;

import java.util.Set;

import com.example.tazminathesap.model.BaseEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommonController <E extends BaseEntity> {
    @GetMapping
    ResponseEntity<Set<E>> fetchAll();

    @GetMapping("/{id}")
    ResponseEntity<E> fetchById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<E> create(@RequestBody E entity);

    @DeleteMapping
    ResponseEntity<E> deleteById(@PathVariable("id") Long id);
}
