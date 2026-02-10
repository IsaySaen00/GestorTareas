package com.aicom.tareas.application.service;

import com.aicom.tareas.application.port.in.TareaUseCase;
import com.aicom.tareas.application.port.out.TareaRepositoryPort;
import com.aicom.tareas.domain.model.Tarea;
import org.springframework.stereotype.Service;

@Service
public class TareaService implements TareaUseCase {

    private final TareaRepositoryPort tareaRepositoryPort;

    public TareaService(TareaRepositoryPort tareaRepositoryPort){
        this.tareaRepositoryPort = tareaRepositoryPort;
    }

    @Override
    public Tarea crearTarea(Tarea tarea) {
        return tareaRepositoryPort.guardar(tarea);
    }
}
