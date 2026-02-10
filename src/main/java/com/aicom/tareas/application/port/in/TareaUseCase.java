package com.aicom.tareas.application.port.in;

import com.aicom.tareas.domain.model.Tarea;

public interface TareaUseCase {
    Tarea crearTarea(Tarea tarea);
}
