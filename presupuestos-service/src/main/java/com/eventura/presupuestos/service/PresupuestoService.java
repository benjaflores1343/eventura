package com.eventura.presupuestos.service;

import com.eventura.presupuestos.model.Presupuesto;
import com.eventura.presupuestos.repository.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresupuestoService {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    public Presupuesto guardar(Presupuesto presupuesto) {
        return presupuestoRepository.save(presupuesto);
    }

    public List<Presupuesto> listarPorEvento(Long eventoId) {
        return presupuestoRepository.findByEventoId(eventoId);
    }

    public Double totalGastado(Long eventoId) {
        return presupuestoRepository.findByEventoId(eventoId)
            .stream()
            .mapToDouble(Presupuesto::getMonto)
            .sum();
    }
}
