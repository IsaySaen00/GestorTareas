package com.aicom.tareas.application.port.in;

import com.aicom.tareas.domain.model.Tarea;

import java.util.List;

public interface TareaUseCase {
    Tarea crearTarea(Tarea tarea);
    List<Tarea> obtenerTareas();
    Tarea obtnerPorId(Long id);
}
