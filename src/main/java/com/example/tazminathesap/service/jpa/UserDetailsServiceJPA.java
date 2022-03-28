package com.example.tazminathesap.service.jpa;

import com.example.tazminathesap.model.User;
import com.example.tazminathesap.model.UserDetailsImpl;
import com.example.tazminathesap.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceJPA implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return UserDetailsImpl.build(user);
    }

}
