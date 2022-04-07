package com.example.tazminathesap.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private List<String> roles;
    private String refreshToken;
    private String accessToken;
    private String accessType = "Bearer";;

    public JwtResponse(String accessToken, String refreshToken, Long id, String name, String lastName, String email, List<String> roles){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

}
