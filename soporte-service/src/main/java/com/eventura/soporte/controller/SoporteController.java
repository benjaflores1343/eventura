package com.eventura.soporte.controller;

import com.eventura.soporte.model.Soporte;
import com.eventura.soporte.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @PostMapping("/registrar")
    public Soporte registrar(@RequestBody Soporte soporte) {
        return soporteService.guardar(soporte);
    }

    @GetMapping("/evento/{eventoId}")
    public List<Soporte> listar(@PathVariable Long eventoId, @RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return soporteService.listarPorEventoYUsuario(eventoId, usuarioId);
        } else {
            return soporteService.listarPorEvento(eventoId);
        }
    }

    @PutMapping("/estado/{id}")
    public Soporte cambiarEstado(@PathVariable Long id, @RequestBody String estado) {
        return soporteService.actualizarEstado(id, estado);
    }
}
