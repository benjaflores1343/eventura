package com.example.invitados_service.repository;

import com.example.invitados_service.model.Invitado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitadoRepository extends JpaRepository<Invitado, Long> {
    Optional<Invitado> findByEmail(String email);
}
