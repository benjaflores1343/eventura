package com.example.invitados_service.controller;

import com.example.invitados_service.model.Invitado;
import com.example.invitados_service.service.InvitadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/invitados")
public class InvitadoController {

    @Autowired
    private InvitadoService invitadoService;

    @Operation(summary = "Registrar un nuevo invitado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invitado registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/registrar")
    public ResponseEntity<EntityModel<Invitado>> registrar(@RequestBody Invitado invitado) {
        if (invitado.getNombre() == null || invitado.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (invitado.getEmail() == null || invitado.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (invitado.getPassword() == null || invitado.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Invitado invitadoGuardado = invitadoService.registrarInvitado(invitado);
        EntityModel<Invitado> resource = EntityModel.of(invitadoGuardado);
        resource.add(WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).registrar(invitado)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @Operation(summary = "Iniciar sesión de invitado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<EntityModel<Invitado>> iniciarSesion(@RequestParam String email, @RequestParam String password) {
        Optional<Invitado> invitadoOpt = invitadoService.iniciarSesion(email, password);
        if (invitadoOpt.isPresent()) {
            EntityModel<Invitado> resource = EntityModel.of(invitadoOpt.get());
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).iniciarSesion(email, password)).withSelfRel());
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Operation(summary = "Actualizar perfil de invitado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil actualizado"),
            @ApiResponse(responseCode = "400", description = "ID del invitado obligatorio")
    })
    @PutMapping("/perfil")
    public ResponseEntity<EntityModel<Invitado>> actualizarPerfil(@RequestBody Invitado invitado) {
        if (invitado.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Invitado invitadoActualizado = invitadoService.actualizarPerfil(invitado);
        EntityModel<Invitado> resource = EntityModel.of(invitadoActualizado);
        resource.add(WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).actualizarPerfil(invitado)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

}
