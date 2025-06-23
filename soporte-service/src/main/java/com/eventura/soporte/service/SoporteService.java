package com.eventura.soporte.service;

import com.eventura.soporte.model.Soporte;
import com.eventura.soporte.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public Soporte guardar(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

    public List<Soporte> listarPorEvento(Long eventoId) {
        return soporteRepository.findByEventoId(eventoId);
    }

    public List<Soporte> listarPorEventoYUsuario(Long eventoId, Long usuarioId) {
        return soporteRepository.findByEventoIdAndUsuarioId(eventoId, usuarioId);
    }

    public Soporte actualizarEstado(Long id, String estado) {
        Soporte soporte = soporteRepository.findById(id).orElse(null);
        if (soporte != null) {
            soporte.setEstado(estado);
            return soporteRepository.save(soporte);
        }
        return null;
    }
}
