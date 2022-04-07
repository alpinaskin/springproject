package com.example.tazminathesap.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.example.tazminathesap.model.User;
import com.example.tazminathesap.service.UserService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController extends BaseController<User, UserService> {

    public UserController(UserService service, GenericModelAssembler<User> assembler) {
        super(service, assembler);
    }
    
    @PostMapping("/findByName")
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUserByName(String name){
        List<EntityModel<User>> users = service.findByName(name).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList()); 
        
        return ResponseEntity.ok(CollectionModel.of(users, 
        linkTo(methodOn(UserController.class).getUserByName(name)).withSelfRel()));
    }
}
