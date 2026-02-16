package com.aicom.tareas.application.port.out;

import com.aicom.tareas.domain.model.Tarea;

import java.util.List;

public interface TareaRepositoryPort {
    Tarea guardar(Tarea tarea);
    List<Tarea> listarTareas();
    Tarea listarPorId(Long id);
}
