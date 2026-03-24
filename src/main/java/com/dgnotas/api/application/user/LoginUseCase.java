package com.dgnotas.api.application.user;

import com.dgnotas.api.domain.model.User;
import com.dgnotas.api.domain.repository.UserRepository;
import com.dgnotas.api.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginUseCase {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public LoginUseCase(UserRepository repository,
                        PasswordEncoder encoder,
                        JwtService jwtService) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public String execute(String email, String password) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.generateToken(user);
    }
}
