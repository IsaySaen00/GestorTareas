package com.aicom.tareas.application.service;

import com.aicom.tareas.application.port.out.TareaRepositoryPort;
import com.aicom.tareas.domain.model.Tarea;
import com.aicom.tareas.infrastructure.persistence.jpa.TareaJPA;
import com.aicom.tareas.infrastructure.persistence.mapper.TareaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //Test de creación de tarea
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

    //test para validar si mapper de tarea funciona
    @Test
    public void testConverterTarea(){
        TareaMapper mapper = Mappers.getMapper(TareaMapper.class);

        TareaJPA tareaJPA = new TareaJPA(1L, "Test", "Test con JUnit y Mockito", "Por hacer", null);
        Tarea tarea = new Tarea(1L, "Test", "Test con JUnit y Mockito", "Por hacer", null);

        assertEquals(tarea.getId(), mapper.convertTareaJPAtoTarea(Optional.of(tareaJPA)).getId());
        assertEquals(tarea.getTitulo(), mapper.convertTareaJPAtoTarea(Optional.of(tareaJPA)).getTitulo());
        assertEquals(tarea.getDescripcion(), mapper.convertTareaJPAtoTarea(Optional.of(tareaJPA)).getDescripcion());
        assertEquals(tarea.getEstado(), mapper.convertTareaJPAtoTarea(Optional.of(tareaJPA)).getEstado());

    }


    @Test
    public void testGetAll(){

        List<Tarea> tareasSimuladas = List.of(
                new Tarea(1L, "Test 1", "Desc 1", "Pendiente", null),
                new Tarea(2L, "Test 2", "Desc 2", "Completada", null)
        );

        when(repository.listarTareas())
                .thenReturn(tareasSimuladas);

        List<Tarea> resultado = tareaService.obtenerTareas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }
}
