package com.eventura.presupuestos.controller;

import com.eventura.presupuestos.model.Presupuesto;
import com.eventura.presupuestos.service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @PostMapping("/registrar")
    public EntityModel<Presupuesto> registrar(@RequestBody Presupuesto presupuesto) {
        Presupuesto saved = presupuestoService.guardar(presupuesto);
        return EntityModel.of(saved,
                WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).registrar(presupuesto)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).listar(saved.getEventoId(), null)).withRel("listar"));
    }

    @GetMapping("/evento/{eventoId}")
    public CollectionModel<EntityModel<Presupuesto>> listar(@PathVariable Long eventoId, @RequestParam(required = false) Long usuarioId) {
        List<Presupuesto> presupuestos;
        if (usuarioId != null) {
            presupuestos = presupuestoService.listarPorEventoYUsuario(eventoId, usuarioId);
        } else {
            presupuestos = presupuestoService.listarPorEvento(eventoId);
        }

        List<EntityModel<Presupuesto>> presupuestoModels = presupuestos.stream()
                .map(p -> EntityModel.of(p,
                        WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).listar(eventoId, usuarioId)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).total(eventoId, usuarioId)).withRel("total")))
                .collect(Collectors.toList());

        return CollectionModel.of(presupuestoModels,
                WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).listar(eventoId, usuarioId)).withSelfRel());
    }

    @GetMapping("/total/{eventoId}")
    public EntityModel<Double> total(@PathVariable Long eventoId, @RequestParam(required = false) Long usuarioId) {
        Double total;
        if (usuarioId != null) {
            total = presupuestoService.totalGastado(eventoId, usuarioId);
        } else {
            total = presupuestoService.totalGastado(eventoId);
        }
        return EntityModel.of(total,
                WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).total(eventoId, usuarioId)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).listar(eventoId, usuarioId)).withRel("listar"));
    }

    @PutMapping("/modificar")
    public EntityModel<Presupuesto> modificar(@RequestBody Presupuesto presupuesto) {
        Presupuesto updated = presupuestoService.guardar(presupuesto);
        return EntityModel.of(updated,
                WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).modificar(presupuesto)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PresupuestoController.class).listar(updated.getEventoId(), null)).withRel("listar"));
    }
}
