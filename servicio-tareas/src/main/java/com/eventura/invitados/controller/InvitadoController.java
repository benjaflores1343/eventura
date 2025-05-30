package com.eventura.invitados.controller;

import com.eventura.invitados.model.Invitado;
import com.eventura.invitados.service.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invitados")
public class InvitadoController {

    @Autowired
    private InvitadoService invitadoService;

    @PostMapping("/registrar")
    public Invitado registrar(@RequestBody Invitado invitado) {
        return invitadoService.guardar(invitado);
    }

    @GetMapping("/evento/{eventoId}")
    public List<Invitado> listar(@PathVariable Long eventoId) {
        return invitadoService.listarPorEvento(eventoId);
    }

    @PutMapping("/confirmar/{id}")
    public Invitado confirmar(@PathVariable Long id) {
        return invitadoService.confirmarAsistencia(id);
    }
}
