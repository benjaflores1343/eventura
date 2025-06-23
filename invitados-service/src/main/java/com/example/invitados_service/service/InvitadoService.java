package com.example.invitados_service.service;

import com.example.invitados_service.model.Invitado;
import com.example.invitados_service.repository.InvitadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class InvitadoService {

    @Autowired
    private InvitadoRepository invitadoRepository;

    public Invitado registrarInvitado(Invitado invitado) {
        // Aquí puedes agregar validaciones y lógica adicional
        return invitadoRepository.save(invitado);
    }

    public Optional<Invitado> iniciarSesion(String email, String password) {
        Optional<Invitado> invitadoOpt = invitadoRepository.findByEmail(email);
        if (invitadoOpt.isPresent()) {
            Invitado invitado = invitadoOpt.get();
            if (invitado.getPassword().equals(password)) {
                return Optional.of(invitado);
            }
        }
        return Optional.empty();
    }

    public Invitado actualizarPerfil(Invitado invitado) {
        // Aquí puedes agregar validaciones y lógica adicional
        return invitadoRepository.save(invitado);
    }

    public Optional<Invitado> obtenerPorId(Long id) {
        return invitadoRepository.findById(id);
    }

    // Eliminado método listarPorEvento porque la entidad Invitado no tiene propiedad eventoId
    // public List<Invitado> listarPorEvento(Long eventoId) {
    //     return invitadoRepository.findByEventoId(eventoId);
}
