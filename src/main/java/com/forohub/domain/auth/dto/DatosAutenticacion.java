package com.forohub.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank String login,
        @NotBlank String clave
) {
}
