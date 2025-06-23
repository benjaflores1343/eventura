package com.eventura.eventservice.controllers;

import com.eventura.eventservice.models.Event;
import com.eventura.eventservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para eventos con soporte HATEOAS.
 */
@RestController
@RequestMapping("/api/hateoas/events")
public class EventControllerHateoas {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Event>> getEventById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Event> resource = EntityModel.of(event);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventControllerHateoas.class).getEventById(id)).withSelfRel();
        Link allEventsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventControllerHateoas.class).getAllEvents()).withRel("all-events");
        resource.add(selfLink, allEventsLink);
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Event>>> getAllEvents() {
        List<EntityModel<Event>> events = eventService.findAll().stream()
                .map(event -> {
                    EntityModel<Event> resource = EntityModel.of(event);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventControllerHateoas.class).getEventById(event.getId())).withSelfRel();
                    resource.add(selfLink);
                    return resource;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }
}
