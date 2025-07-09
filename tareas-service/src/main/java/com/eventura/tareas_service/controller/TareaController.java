package com.eventura.tareas_service.controller;

import com.eventura.tareas_service.model.Tarea;
import com.eventura.tareas_service.service.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "API para gestión de tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Operation(summary = "Registrar una nueva tarea")
    @PostMapping("/registrar")
    public Tarea registrar(@RequestBody Tarea tarea) {
        try {
            return tareaService.guardar(tarea);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Listar tareas por evento")
    @GetMapping("/evento/{eventoId}")
    public List<Tarea> listar(@PathVariable Long eventoId) {
        return tareaService.listarPorEvento(eventoId);
    }

    @Operation(summary = "Marcar una tarea como completada")
    @PutMapping("/completar/{id}")
    public Tarea completar(@PathVariable Long id) {
        return tareaService.marcarComoCompletada(id);
    }
}
