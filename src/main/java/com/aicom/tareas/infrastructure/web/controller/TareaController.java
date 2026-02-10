package com.aicom.tareas.infrastructure.web.controller;

import com.aicom.tareas.application.port.in.TareaUseCase;
import com.aicom.tareas.domain.model.Tarea;
import com.aicom.tareas.infrastructure.web.dto.CrearTareaRequest;
import com.aicom.tareas.infrastructure.web.dto.TareaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaUseCase tareaUseCase;

    public TareaController(TareaUseCase tareaUseCase){
        this.tareaUseCase = tareaUseCase;
    }

    @PostMapping
    public ResponseEntity<TareaResponse> crear(@RequestBody CrearTareaRequest request){

        Tarea tarea = Tarea.paraCrear(request.titulo(), request.descripcion());

        Tarea creada = tareaUseCase.crearTarea(tarea);

        TareaResponse response = new TareaResponse(
                creada.getId(),
                creada.getTitulo(),
                creada.getDescripcion(),
                creada.getEstado(),
                creada.getFechaCreacion()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
