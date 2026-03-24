package com.dgnotas.api.application.user;

import com.dgnotas.api.domain.model.User;
import com.dgnotas.api.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterUseCase {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public RegisterUseCase(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User execute(String email, String password) {

        if (repository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        String encoded = encoder.encode(password);

        User user = new User(email, encoded);

        return repository.save(user);
    }
}
