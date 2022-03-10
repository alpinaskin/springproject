package com.example.tazminathesap.controller;

import java.util.Set;

import com.example.tazminathesap.model.BaseEntity;
import com.example.tazminathesap.service.CrudService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

//TODO: Create Logging

public abstract class BaseController<E extends BaseEntity, S extends CrudService<E>> implements CommonController<E> {
   Logger logger = LoggerFactory.getLogger(BaseController.class); 
   private final S service;

    @Autowired 
    protected BaseController(S service)
     {
        this.service = service;
     }

   @Override
   public ResponseEntity<Set<E>> fetchAll() {
      try {
         if(service.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         
         Set<E> tempEntity = service.findAll();
         logger.info("Bütün modeller sıralanıyor: "+ tempEntity.toString());
         return new ResponseEntity<>((Set<E>)tempEntity, HttpStatus.OK);
         
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }     
   }

   @Override
   public ResponseEntity<E> fetchById(@PathVariable("id") Long id) {
      try {
         E tempE = service.findById(id);
         if(tempE == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         logger.info("Alınan model: " + tempE);
         return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   };

   @Override
   public ResponseEntity<E> create(@RequestBody E entity) {
      try {
         E tempE = service.save(entity);
         logger.info("Model oluşturuldu: "+ tempE.toString());
         return new ResponseEntity<>(tempE, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @Override
   public ResponseEntity<E> deleteById(@PathVariable("id") Long id)
   {
      //TODO: Edit update operation
      try {
         E tempE = service.findById(id);
         if(tempE == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         service.deleteById(id);
         logger.info("Silinen Model: "+ tempE);
         return new ResponseEntity<>(null, HttpStatus.OK);
      } catch (Exception e) {
         //TODO: handle exception
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
}
