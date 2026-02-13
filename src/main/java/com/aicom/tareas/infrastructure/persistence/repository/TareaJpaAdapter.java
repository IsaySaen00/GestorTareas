package com.aicom.tareas.infrastructure.persistence.repository;

import com.aicom.tareas.application.port.out.TareaRepositoryPort;
import com.aicom.tareas.domain.model.Tarea;
import com.aicom.tareas.infrastructure.persistence.jpa.TareaJPA;
import org.springframework.stereotype.Repository;

@Repository
public class TareaJpaAdapter implements TareaRepositoryPort {

    private final SpringTareaRepository springTareaRepository;

    public TareaJpaAdapter(SpringTareaRepository springTareaRepository){
        this.springTareaRepository = springTareaRepository;
    }

    @Override
    public Tarea guardar(Tarea tarea) {
        TareaJPA tareaJPA = new TareaJPA();

        tareaJPA.setTitulo(tarea.getTitulo());
        tareaJPA.setDescripcion(tarea.getDescripcion());
        tareaJPA.setEstado(tarea.getEstado());
        tareaJPA.setFechaCreacion(tarea.getFechaCreacion());

        final TareaJPA tareaGuardada = springTareaRepository.save(tareaJPA);

        return new Tarea(
                tareaGuardada.getId(),
                tareaGuardada.getTitulo(),
                tareaGuardada.getDescripcion(),
                tareaGuardada.getEstado(),
                tareaGuardada.getFechaCreacion()
        );
    }
}
