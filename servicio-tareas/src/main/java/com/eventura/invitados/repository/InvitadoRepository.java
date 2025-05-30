package com.eventura.invitados.repository;

import com.eventura.invitados.model.Invitado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitadoRepository extends JpaRepository<Invitado, Long> {
    List<Invitado> findByEventoId(Long eventoId);
}
