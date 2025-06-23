package com.eventura.eventservice.services;

import com.eventura.eventservice.models.Event;
import com.eventura.eventservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findByUsuarioId(Long usuarioId) {
        return eventRepository.findByOrganizerId(usuarioId.toString());
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
