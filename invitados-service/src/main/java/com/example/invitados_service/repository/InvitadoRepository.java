package com.example.invitados_service.repository;

import com.example.invitados_service.model.Invitado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import java.util.List;

public interface InvitadoRepository extends JpaRepository<Invitado, Long> {
    Optional<Invitado> findByEmail(String email);
    // Eliminado método findByEventoId porque la entidad Invitado no tiene propiedad eventoId
    // List<Invitado> findByEventoId(Long eventoId);
}
