package com.example.tazminathesap.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.tazminathesap.model.BaseEntity;

@Component
public class GenericModelAssembler<E extends BaseEntity> implements RepresentationModelAssembler<E, EntityModel<E>>{

    @Override
    public EntityModel<E> toModel(E entity) {
        //System.out.println(methodOn(BaseController.class).fetchAll());
        String entityName = entity.getClass().getSimpleName().toLowerCase();
        return EntityModel.of(entity, 
            linkTo(BaseController.class).slash(entityName).slash(entity.getId()).withSelfRel(),
                linkTo(BaseController.class).slash(entityName).withRel(entityName));
    }
}
