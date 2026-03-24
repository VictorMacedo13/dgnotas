package com.dgnotas.api.domain.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String id;
    private String email;
    private String password;
    private List<String> roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.roles = List.of("USER");
    }
}
