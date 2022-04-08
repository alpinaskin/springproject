package com.example.tazminathesap.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private Long id;
    private String name;
    private String lastName;
    private String email;
}
