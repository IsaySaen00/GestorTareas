package com.aicom.tareas.infrastructure.persistence.repository;

import com.aicom.tareas.application.port.out.TareaRepositoryPort;
import com.aicom.tareas.domain.model.Tarea;
import com.aicom.tareas.infrastructure.persistence.jpa.TareaJPA;
import com.aicom.tareas.infrastructure.persistence.mapper.TareaMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TareaJpaAdapter implements TareaRepositoryPort {

    private final SpringTareaRepository springTareaRepository;
    private final TareaMapper tareaMapper;

    public TareaJpaAdapter(SpringTareaRepository springTareaRepository, TareaMapper tareaMapper){
        this.springTareaRepository = springTareaRepository;
        this.tareaMapper = tareaMapper;
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

    @Override
    public List<Tarea> listarTareas() {
        return tareaMapper.toTareaList(springTareaRepository.findAll());
    }

    @Override
    public Tarea listarPorId(Long id) {
        return springTareaRepository.findById(id).map(tareaMapper::convertTareaJPAtoTarea).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }
}
