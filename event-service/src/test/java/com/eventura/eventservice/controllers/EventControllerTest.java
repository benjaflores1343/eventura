package com.eventura.eventservice.controllers;

import com.eventura.eventservice.models.Event;
import com.eventura.eventservice.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para EventController.
 */
public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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

        List<Event> events = eventController.getAll(null);

        assertNotNull(events);
        assertEquals(2, events.size());
        verify(eventService, times(1)).findAll();
    }
}
