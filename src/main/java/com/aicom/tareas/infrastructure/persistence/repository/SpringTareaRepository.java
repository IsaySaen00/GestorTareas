package com.aicom.tareas.infrastructure.persistence.repository;

import com.aicom.tareas.infrastructure.persistence.jpa.TareaJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringTareaRepository extends JpaRepository<TareaJPA, Long> {
}
