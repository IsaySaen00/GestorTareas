package com.aicom.tareas.infrastructure.web.dto;

public record ActualizarTareaRequest(
        String titulo,
        String descripcion,
        String estado
) {
}
