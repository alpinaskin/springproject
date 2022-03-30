package com.example.tazminathesap.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    public UserDetails getUserByEmail(String email);
}
