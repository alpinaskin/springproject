package com.example.tazminathesap.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.example.tazminathesap.exception.NotFoundException;
import com.example.tazminathesap.model.BaseEntity;
import com.example.tazminathesap.service.CrudService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
public abstract class BaseController<E extends BaseEntity, S extends CrudService<E>> implements CommonController<E> {
   Logger logger = LoggerFactory.getLogger(BaseController.class);
   protected final S service;
   protected final GenericModelAssembler<E> assembler;

   @Autowired
   protected BaseController(S service, GenericModelAssembler<E> assembler) {
      this.service = service;
      this.assembler = assembler;
   }

   @Override
   @PreAuthorize("hasRole('USER')")
   public ResponseEntity<CollectionModel<EntityModel<E>>> fetchAll() {
      List<EntityModel<E>> entities = service.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

      return ResponseEntity.ok(
            CollectionModel.of(entities,
                  linkTo(methodOn(BaseController.class).fetchAll()).withSelfRel()));
   }

   @Override
   public ResponseEntity<EntityModel<E>> fetchById(@PathVariable("id") Long id) {
      E entity = service.findById(id)
            .orElseThrow(() -> new NotFoundException("Id numarası" + id + " olan model bulunamadı!"));

      return ResponseEntity.ok(assembler.toModel(entity));
   };

   @Override
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<?> create(@RequestBody E entity) {
      E savedEntity = service.save(entity);

      EntityModel<E> entityResource = assembler.toModel(savedEntity);

      return ResponseEntity
            .created(entityResource.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityResource);
   }

   @Override
   public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody E entity) {
      E updatedEntity = service.update(entity, id);

      EntityModel<E> entityResource = assembler.toModel(updatedEntity);

      return ResponseEntity.created(entityResource.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityResource);

   }

   @Override
   public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
      service.deleteById(id);

      return ResponseEntity.noContent().build();
   }
}
