package com.eventura.tareas_service.service;

import com.eventura.tareas_service.model.Tarea;
import com.eventura.tareas_service.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Tarea guardar(Tarea tarea) {
        // Validar que la fechaLimite no sea pasada
        try {
            LocalDate fechaLimite = LocalDate.parse(tarea.getFechaLimite(), DateTimeFormatter.ISO_DATE);
            if (fechaLimite.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha límite no puede ser una fecha pasada.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use formato ISO yyyy-MM-dd.");
        }
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
