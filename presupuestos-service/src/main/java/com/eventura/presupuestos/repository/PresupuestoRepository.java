package com.eventura.presupuestos.repository;

import com.eventura.presupuestos.model.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {
    List<Presupuesto> findByEventoId(Long eventoId);
    List<Presupuesto> findByEventoIdAndUsuarioId(Long eventoId, Long usuarioId);
}
