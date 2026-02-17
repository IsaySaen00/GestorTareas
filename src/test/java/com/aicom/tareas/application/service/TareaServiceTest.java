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

        assertEquals(tarea.getId(), mapper.convertTareaJPAtoTarea(tareaJPA).getId());
        assertEquals(tarea.getTitulo(), mapper.convertTareaJPAtoTarea(tareaJPA).getTitulo());
        assertEquals(tarea.getDescripcion(), mapper.convertTareaJPAtoTarea(tareaJPA).getDescripcion());
        assertEquals(tarea.getEstado(), mapper.convertTareaJPAtoTarea(tareaJPA).getEstado());

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

    @Test
    void shouldUpdateTask() {
        Long id = 1L;
        Tarea tareaConCambios = new Tarea(id, "Titulo Actualizado", "Desc Actualizada", "EN_PROGRESO", null);

        Tarea tareaActualizada = new Tarea(id, "Titulo Actualizado", "Desc Actualizada", "EN_PROGRESO", null);

        when(repository.cambioParcialTarea(eq(id), any(Tarea.class)))
                .thenReturn(tareaActualizada);

        Tarea resultado = tareaService.actualizarTarea(id, tareaConCambios);

        assertNotNull(resultado);
        assertEquals("Titulo Actualizado", resultado.getTitulo());
        assertEquals("EN_PROGRESO", resultado.getEstado());

        verify(repository, times(1)).cambioParcialTarea(eq(id), any(Tarea.class));
    }

    @Test
    void shouldReturnNullWhenUpdateNonExistentTask() {
        Long id = 99L;
        Tarea tareaConCambios = new Tarea(id, "Titulo", "Desc", "ESTADO", null);

        when(repository.cambioParcialTarea(eq(id), any(Tarea.class))).thenReturn(null);

        Tarea resultado = tareaService.actualizarTarea(id, tareaConCambios);

        assertNull(resultado);
    }

    @Test
    void shouldDeleteTask() {
        Long id = 1L;

        when(repository.eliminacionTarea(id)).thenReturn(true);

        boolean resultado = tareaService.eliminarTarea(id);

        assertTrue(resultado);
        verify(repository, times(1)).eliminacionTarea(id);
    }

    @Test
    void shouldReturnFalseWhenDeleteNonExistentTask() {
        Long id = 99L;

        when(repository.eliminacionTarea(id)).thenReturn(false);

        boolean resultado = tareaService.eliminarTarea(id);

        assertFalse(resultado);
        verify(repository, times(1)).eliminacionTarea(id);
    }
}
