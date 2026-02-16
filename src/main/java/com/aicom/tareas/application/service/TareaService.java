package com.aicom.tareas.application.service;

import com.aicom.tareas.application.port.in.TareaUseCase;
import com.aicom.tareas.application.port.out.TareaRepositoryPort;
import com.aicom.tareas.domain.model.Tarea;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Tarea> obtenerTareas() {
        return tareaRepositoryPort.listarTareas();
    }

    @Override
    public Tarea obtnerPorId(Long id) {
        return tareaRepositoryPort.listarPorId(id);
    }

    @Override
    public Tarea actualizarTarea(Long id, Tarea tareaCambios) {
        return tareaRepositoryPort.cambioParcialTarea(id, tareaCambios);
    }

    @Override
    public boolean EliminarTarea(Long id) {
        return tareaRepositoryPort.eliminacionTarea(id);
    }
}
