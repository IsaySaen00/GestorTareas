package com.aicom.tareas.application.service;

import com.aicom.tareas.application.port.out.TareaRepositoryPort;
import com.aicom.tareas.domain.model.Tarea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TareaServiceTest {

    @Mock
    private TareaRepositoryPort repository;

    private TareaService tareaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        tareaService = new TareaService(repository);
    }

    @Test
    void shouldCreateTask(){
        Tarea tareaEntrada = Tarea.paraCrear("Test", "Test con JUnit y Mockito");
        Tarea tareaGuardada = new Tarea(1L, "Test", "Test con JUnit y Mockito", "Por hacer", null);

        when(repository.guardar(any(Tarea.class)))
                .thenReturn(tareaGuardada);

        Tarea resultado = tareaService.crearTarea(tareaGuardada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Test", resultado.getTitulo());

        verify(repository, times(1)).guardar(any(Tarea.class));
    }
}
