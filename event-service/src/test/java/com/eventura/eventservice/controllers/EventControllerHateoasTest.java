package com.eventura.eventservice.controllers;

import com.eventura.eventservice.models.Event;
import com.eventura.eventservice.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
  Pruebas unitarias para EventControllerHateoas.
 */
public class EventControllerHateoasTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventControllerHateoas eventControllerHateoas;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEventById() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Evento Test");

        when(eventService.findById(1L)).thenReturn(event);

        ResponseEntity<EntityModel<Event>> response = eventControllerHateoas.getEventById(1L);

        assertNotNull(response);
        assertTrue(response.hasBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(event, response.getBody().getContent());

        verify(eventService, times(1)).findById(1L);
    }

    @Test
    public void testGetAllEvents() {
        Event event1 = new Event();
        event1.setId(1L);
        event1.setName("Evento 1");

        Event event2 = new Event();
        event2.setId(2L);
        event2.setName("Evento 2");

        when(eventService.findAll()).thenReturn(Arrays.asList(event1, event2));

        ResponseEntity<List<EntityModel<Event>>> response = eventControllerHateoas.getAllEvents();

        assertNotNull(response);
        assertTrue(response.hasBody());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());

        verify(eventService, times(1)).findAll();
    }
}
