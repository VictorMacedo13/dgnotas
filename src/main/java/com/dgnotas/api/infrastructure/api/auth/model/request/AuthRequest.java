package com.dgnotas.api.infrastructure.api.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de autenticação")
public record AuthRequest(
        @Schema(description = "E-mail do usuário", example = "user@dgnotas.com") String email,
        @Schema(description = "Senha do usuário", example = "senha123") String password
) {
}
