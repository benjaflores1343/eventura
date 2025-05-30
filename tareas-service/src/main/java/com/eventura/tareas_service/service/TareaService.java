package com.eventura.tareas_service.service;

import com.eventura.tareas_service.model.Tarea;
import com.eventura.tareas_service.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public List<Tarea> listarPorEvento(Long eventoId) {
        return tareaRepository.findByEventoId(eventoId);
    }

    public Tarea marcarComoCompletada(Long id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea != null) {
            tarea.setCompletada(true);
            return tareaRepository.save(tarea);
        }
        return null;
    }
}
