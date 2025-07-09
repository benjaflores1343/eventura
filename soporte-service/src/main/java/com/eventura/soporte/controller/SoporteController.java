package com.eventura.soporte.controller;

import com.eventura.soporte.model.Soporte;
import com.eventura.soporte.service.SoporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
@Tag(name = "Soporte", description = "API para gestión de soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @Operation(summary = "Registrar un nuevo soporte")
    @PostMapping("/registrar")
    public Soporte registrar(@RequestBody Soporte soporte) {
        return soporteService.guardar(soporte);
    }

    @Operation(summary = "Listar soportes por evento y opcionalmente por usuario")
    @GetMapping("/evento/{eventoId}")
    public List<Soporte> listar(@PathVariable Long eventoId, @RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return soporteService.listarPorEventoYUsuario(eventoId, usuarioId);
        } else {
            return soporteService.listarPorEvento(eventoId);
        }
    }

    @Operation(summary = "Cambiar estado de un soporte")
    @PutMapping("/estado/{id}")
    public Soporte cambiarEstado(@PathVariable Long id, @RequestBody String estado) {
        return soporteService.actualizarEstado(id, estado);
    }
}
