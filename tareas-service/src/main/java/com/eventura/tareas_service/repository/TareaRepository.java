package com.eventura.tareas_service.repository;

import com.eventura.tareas_service.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByEventoId(Long eventoId);
}
