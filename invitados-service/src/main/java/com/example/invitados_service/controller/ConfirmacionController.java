package com.example.invitados_service.controller;

import com.example.invitados_service.model.Invitado;
import com.example.invitados_service.service.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/confirmacion")
public class ConfirmacionController {

    @Autowired
    private InvitadoService invitadoService;

    @PutMapping("/asistencia/{id}")
    public ResponseEntity<?> confirmarAsistencia(@PathVariable Long id,
                                                 @RequestBody ConfirmacionRequest request) {
        Optional<Invitado> invitadoOpt = invitadoService.obtenerPorId(id);
        if (invitadoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invitado no encontrado");
        }
        Invitado invitado = invitadoOpt.get();
        invitado.setAsistenciaConfirmada(request.isAsiste());
        invitado.setRestriccionesAlimentarias(request.getRestriccionesAlimentarias());
        invitadoService.registrarInvitado(invitado);
        return new ResponseEntity<>(invitado, HttpStatus.OK);
    }

    public static class ConfirmacionRequest {
        private boolean asiste;
        private String restriccionesAlimentarias;

        public boolean isAsiste() {
            return asiste;
        }

        public void setAsiste(boolean asiste) {
            this.asiste = asiste;
        }

        public String getRestriccionesAlimentarias() {
            return restriccionesAlimentarias;
        }

        public void setRestriccionesAlimentarias(String restriccionesAlimentarias) {
            this.restriccionesAlimentarias = restriccionesAlimentarias;
        }
    }
}
