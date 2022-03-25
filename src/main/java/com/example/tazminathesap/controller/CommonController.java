package com.example.tazminathesap.controller;

import com.example.tazminathesap.model.BaseEntity;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/")
public interface CommonController <E extends BaseEntity> {
    @GetMapping
    ResponseEntity<CollectionModel<EntityModel<E>>> fetchAll();

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<E>> fetchById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<?> create(@RequestBody E entity);

    @DeleteMapping
    ResponseEntity<?> deleteById(@PathVariable("id") Long id);
}
