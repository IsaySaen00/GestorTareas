package com.aicom.tareas.infrastructure.persistence.mapper;

import com.aicom.tareas.domain.model.Tarea;
import com.aicom.tareas.infrastructure.persistence.jpa.TareaJPA;
import com.aicom.tareas.infrastructure.web.dto.TareaResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TareaMapper {
    Tarea convertTareaJPAtoTarea(TareaJPA tareaJPA);

    TareaResponse convertTareaToResponse(Tarea tarea);

    List<Tarea> toTareaList(List<TareaJPA> tareasJPA);

    List<TareaResponse> toResponseList(List<Tarea> tareas);


}
