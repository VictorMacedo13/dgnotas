package com.dgnotas.api.infrastructure.persistence.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
@Getter
@Setter
public class UserDocument {

    @Id
    private String id;

    private String email;
    private String password;
    private List<String> roles;

}