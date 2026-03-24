package com.dgnotas.api.domain.repository;

import com.dgnotas.api.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByEmail(String email);
}
