package com.aicom.tareas.infrastructure.web.controller;

import com.aicom.tareas.application.port.in.TareaUseCase;
import com.aicom.tareas.domain.model.Tarea;
import com.aicom.tareas.infrastructure.persistence.mapper.TareaMapper;
import com.aicom.tareas.infrastructure.web.dto.CrearTareaRequest;
import com.aicom.tareas.infrastructure.web.dto.TareaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaUseCase tareaUseCase;
    private final TareaMapper tareaMapper;

    public TareaController(TareaUseCase tareaUseCase, TareaMapper tareaMapper) {
        this.tareaUseCase = tareaUseCase;
        this.tareaMapper = tareaMapper;
    }

    @PostMapping
    public ResponseEntity<TareaResponse> crear(@RequestBody CrearTareaRequest request) {

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

    @GetMapping
    public ResponseEntity<List<TareaResponse>> getAll() {

        List<Tarea> tareas = tareaUseCase.obtenerTareas();
        List<TareaResponse> tareaResponse = tareaMapper.toResponseList(tareas);

        return ResponseEntity.ok(tareaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> getById(@PathVariable Long id){
        Tarea tarea = tareaUseCase.obtnerPorId(id);
        TareaResponse response = tareaMapper.convertTareaToResponse(tarea);

        return ResponseEntity.ok(response);
    }
}
