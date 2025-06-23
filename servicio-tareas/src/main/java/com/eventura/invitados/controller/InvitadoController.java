package com.eventura.invitados.controller;

import com.eventura.invitados.model.Invitado;
import com.eventura.invitados.service.InvitadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/invitados")
public class InvitadoController {

    @Autowired
    private InvitadoService invitadoService;

    @PostMapping("/registrar")
    public EntityModel<Invitado> registrar(@RequestBody Invitado invitado) {
        Invitado saved = invitadoService.guardar(invitado);
        return EntityModel.of(saved,
                WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).registrar(invitado)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).listar(saved.getEventoId())).withRel("listar"));
    }

    @GetMapping("/evento/{eventoId}")
    public CollectionModel<EntityModel<Invitado>> listar(@PathVariable Long eventoId) {
        List<EntityModel<Invitado>> invitados = invitadoService.listarPorEvento(eventoId).stream()
                .map(invitado -> EntityModel.of(invitado,
                        WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).listar(eventoId)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).confirmar(invitado.getId())).withRel("confirmar")))
                .collect(Collectors.toList());
        return CollectionModel.of(invitados,
                WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).listar(eventoId)).withSelfRel());
    }

    @PutMapping("/confirmar/{id}")
    public EntityModel<Invitado> confirmar(@PathVariable Long id) {
        Invitado invitado = invitadoService.confirmarAsistencia(id);
        return EntityModel.of(invitado,
                WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).confirmar(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(InvitadoController.class).listar(invitado.getEventoId())).withRel("listar"));
    }
}
