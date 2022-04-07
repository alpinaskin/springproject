package com.example.tazminathesap.service;

import java.util.List;

import com.example.tazminathesap.model.User;

public interface UserService extends CrudService<User>{
 
    public List<User> findByName(String name);

    public User findByEmail(String email);
}
