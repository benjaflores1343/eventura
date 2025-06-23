package com.eventura.eventservice.repositories;

import com.eventura.eventservice.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerId(String organizerId);
}
