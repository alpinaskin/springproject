package com.example.tazminathesap.service.jpa;

import java.util.List;

import com.example.tazminathesap.model.User;
import com.example.tazminathesap.repository.UserRepository;
import com.example.tazminathesap.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceJPA extends AbstractJpaService<User, UserRepository> implements UserService {

    public UserServiceJPA(UserRepository repository) {
        super(repository);
    }

    @Override
    public List<User> findByName(String name) {
        return repository.findByName(name);
    }
    
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
