package com.dgnotas.api.infrastructure.api.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de autenticação")
public record AuthResponse(
        @Schema(description = "Token JWT ou mensagem de status", example = "eyJhbGciOiJIUzI1NiJ9...") String token
) {
}
