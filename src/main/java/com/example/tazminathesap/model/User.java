package com.example.tazminathesap.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    private String name;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(String name, String lastName, String email, String password)
    {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
