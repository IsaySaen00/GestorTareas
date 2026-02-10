package com.aicom.tareas.infrastructure.web.dto;

import java.time.LocalDateTime;

public record TareaResponse(
        Long id,
        String titulo,
        String descripcion,
        String estado,
        LocalDateTime fechaCreacion
) {
}
