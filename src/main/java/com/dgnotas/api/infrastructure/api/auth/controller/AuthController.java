package com.dgnotas.api.infrastructure.api.auth.controller;

import com.dgnotas.api.application.user.LoginUseCase;
import com.dgnotas.api.application.user.RegisterUseCase;
import com.dgnotas.api.domain.model.User;
import com.dgnotas.api.infrastructure.api.auth.model.request.AuthRequest;
import com.dgnotas.api.infrastructure.api.auth.model.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthAPI {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/register")
    @Override
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req) {
        registerUseCase.execute(req.email(), req.password());
        return ResponseEntity.ok(new AuthResponse("user criado"));
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        String token = loginUseCase.execute(req.email(), req.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/public")
    @Override
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("rota publica");
    }

    @GetMapping("/private")
    @Override
    public ResponseEntity<String> privateRoute(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok("olá, " + user.getEmail());
    }
}