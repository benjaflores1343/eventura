package com.example.invitados_service.controller;

import com.example.invitados_service.model.Invitado;
import com.example.invitados_service.service.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/invitados")
public class InvitadoController {

    @Autowired
    private InvitadoService invitadoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Invitado invitado) {
        if (invitado.getNombre() == null || invitado.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre es obligatorio");
        }
        if (invitado.getEmail() == null || invitado.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("El email es obligatorio");
        }
        if (invitado.getPassword() == null || invitado.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("La contraseña es obligatoria");
        }
        Invitado invitadoGuardado = invitadoService.registrarInvitado(invitado);
        return new ResponseEntity<>(invitadoGuardado, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestParam String email, @RequestParam String password) {
        Optional<Invitado> invitadoOpt = invitadoService.iniciarSesion(email, password);
        if (invitadoOpt.isPresent()) {
            return new ResponseEntity<>(invitadoOpt.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @PutMapping("/perfil")
    public ResponseEntity<?> actualizarPerfil(@RequestBody Invitado invitado) {
        if (invitado.getId() == null) {
            return ResponseEntity.badRequest().body("El ID del invitado es obligatorio");
        }
        Invitado invitadoActualizado = invitadoService.actualizarPerfil(invitado);
        return new ResponseEntity<>(invitadoActualizado, HttpStatus.OK);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<?> listarPorEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(invitadoService.listarPorEvento(eventoId));
    }
}
