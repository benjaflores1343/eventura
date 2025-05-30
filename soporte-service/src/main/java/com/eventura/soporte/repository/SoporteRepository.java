package com.eventura.soporte.repository;

import com.eventura.soporte.model.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    List<Soporte> findByEventoId(Long eventoId);
}
