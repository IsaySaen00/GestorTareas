package com.aicom.tareas.infrastructure.web.dto;

public record CrearTareaRequest(
        String titulo,
        String descripcion
) {
}
