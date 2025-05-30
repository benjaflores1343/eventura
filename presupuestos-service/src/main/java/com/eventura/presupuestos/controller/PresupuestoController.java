package com.eventura.presupuestos.controller;

import com.eventura.presupuestos.model.Presupuesto;
import com.eventura.presupuestos.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @PostMapping("/registrar")
    public Presupuesto registrar(@RequestBody Presupuesto presupuesto) {
        return presupuestoService.guardar(presupuesto);
    }

    @GetMapping("/evento/{eventoId}")
    public List<Presupuesto> listar(@PathVariable Long eventoId) {
        return presupuestoService.listarPorEvento(eventoId);
    }

    @GetMapping("/total/{eventoId}")
    public Double total(@PathVariable Long eventoId) {
        return presupuestoService.totalGastado(eventoId);
    }
}
