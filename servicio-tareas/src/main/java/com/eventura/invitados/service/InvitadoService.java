package com.eventura.invitados.service;

import com.eventura.invitados.model.Invitado;
import com.eventura.invitados.repository.InvitadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitadoService {

    @Autowired
    private InvitadoRepository invitadoRepository;

    public Invitado guardar(Invitado invitado) {
        return invitadoRepository.save(invitado);
    }

    public List<Invitado> listarPorEvento(Long eventoId) {
        return invitadoRepository.findByEventoId(eventoId);
    }

    public Invitado confirmarAsistencia(Long id) {
        Invitado invitado = invitadoRepository.findById(id).orElse(null);
        if (invitado != null) {
            invitado.setAsistenciaConfirmada(true);
            return invitadoRepository.save(invitado);
        }
        return null;
    }
}
