package com.dgnotas.api.infrastructure.api.auth.controller;

import com.dgnotas.api.domain.model.User;
import com.dgnotas.api.infrastructure.api.auth.model.request.AuthRequest;
import com.dgnotas.api.infrastructure.api.auth.model.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Autenticação", description = "Endpoints de registro, login e acesso a rotas protegidas")
public interface AuthAPI {

    @Operation(summary = "Registrar novo usuário", description = "Cria uma nova conta com e-mail e senha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req);

    @Operation(summary = "Login", description = "Autentica o usuário e retorna um token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content)
    })
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req);

    @Operation(summary = "Rota pública", description = "Endpoint acessível sem autenticação")
    @ApiResponse(responseCode = "200", description = "OK")
    ResponseEntity<String> publicRoute();

    @Operation(
            summary = "Rota privada",
            description = "Retorna o nome do usuário autenticado. Requer token JWT.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nome do usuário autenticado"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<String> privateRoute(@AuthenticationPrincipal User user);
}
