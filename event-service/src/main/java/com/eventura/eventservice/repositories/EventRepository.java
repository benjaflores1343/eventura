package com.eventura.eventservice.repositories;

import com.eventura.eventservice.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
