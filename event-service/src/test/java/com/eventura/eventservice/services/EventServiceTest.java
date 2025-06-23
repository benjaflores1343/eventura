package com.eventura.eventservice.services;

import com.eventura.eventservice.models.Event;
import com.eventura.eventservice.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para EventService.
 */
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarEvento() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Evento Test");

        when(eventRepository.save(event)).thenReturn(event);

        Event savedEvent = eventService.create(event);

        assertNotNull(savedEvent);
        assertEquals("Evento Test", savedEvent.getName());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    public void testBuscarPorId() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Evento Test");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event foundEvent = eventService.findById(1L);

        assertNotNull(foundEvent);
        assertEquals("Evento Test", foundEvent.getName());
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    public void testBuscarPorId_NotFound() {
        when(eventRepository.findById(2L)).thenReturn(Optional.empty());

        Event foundEvent = eventService.findById(2L);

        assertNull(foundEvent);
        verify(eventRepository, times(1)).findById(2L);
    }
}
