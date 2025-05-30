package com.eventura.tareas_service.controller;

import com.eventura.tareas_service.model.Tarea;
import com.eventura.tareas_service.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @PostMapping("/registrar")
    public Tarea registrar(@RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    @GetMapping("/evento/{eventoId}")
    public List<Tarea> listar(@PathVariable Long eventoId) {
        return tareaService.listarPorEvento(eventoId);
    }



    

    @PutMapping("/completar/{id}")
    public Tarea completar(@PathVariable Long id) {
        return tareaService.marcarComoCompletada(id);
    }
}
