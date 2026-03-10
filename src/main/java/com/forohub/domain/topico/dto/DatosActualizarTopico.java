package com.forohub.domain.topico.dto;

import com.forohub.domain.topico.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String autor,
        @NotBlank String curso,
        @NotNull StatusTopico status
) {
}
