package com.example.tazminathesap.dto.response;

import java.util.List;

import com.example.tazminathesap.model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private List<Role> roles;
}
