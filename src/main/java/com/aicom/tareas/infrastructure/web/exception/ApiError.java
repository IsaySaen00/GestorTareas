package com.aicom.tareas.infrastructure.web.exception;

public record ApiError(
        String code,
        String message
) {
}
