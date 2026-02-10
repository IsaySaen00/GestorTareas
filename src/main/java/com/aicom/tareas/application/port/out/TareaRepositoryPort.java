package com.aicom.tareas.application.port.out;

import com.aicom.tareas.domain.model.Tarea;

public interface TareaRepositoryPort {
    Tarea guardar(Tarea tarea);
}
