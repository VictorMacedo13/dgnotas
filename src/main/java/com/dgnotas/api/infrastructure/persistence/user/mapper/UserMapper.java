package com.dgnotas.api.infrastructure.persistence.user.mapper;


import com.dgnotas.api.domain.model.User;
import com.dgnotas.api.infrastructure.persistence.user.UserDocument;

public class UserMapper {

    public static User toDomain(UserDocument doc) {
        User user = new User(doc.getEmail(), doc.getPassword());
        user.setId(doc.getId());
        user.setRoles(doc.getRoles());
        return user;
    }

    public static UserDocument toDocument(User user) {
        UserDocument doc = new UserDocument();
        doc.setId(user.getId());
        doc.setEmail(user.getEmail());
        doc.setPassword(user.getPassword());
        doc.setRoles(user.getRoles());
        return doc;
    }
}