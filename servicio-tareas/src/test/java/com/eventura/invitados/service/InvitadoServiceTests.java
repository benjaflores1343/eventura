package com.eventura.invitados.service;

import com.eventura.invitados.model.Invitado;
import com.eventura.invitados.repository.InvitadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvitadoServiceTests {

    @Mock
    private InvitadoRepository invitadoRepository;

    @InjectMocks
    private InvitadoService invitadoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Invitado invitado = new Invitado();
        when(invitadoRepository.save(invitado)).thenReturn(invitado);

        Invitado resultado = invitadoService.guardar(invitado);
        assertEquals(invitado, resultado);
        verify(invitadoRepository, times(1)).save(invitado);
    }

    @Test
    public void testListarPorEvento() {
        Long eventoId = 1L;
        List<Invitado> invitados = List.of(new Invitado());
        when(invitadoRepository.findByEventoId(eventoId)).thenReturn(invitados);

        List<Invitado> resultado = invitadoService.listarPorEvento(eventoId);
        assertEquals(invitados, resultado);
        verify(invitadoRepository, times(1)).findByEventoId(eventoId);
    }

    @Test
    public void testConfirmarAsistencia_InvitadoExiste() {
        Invitado invitado = new Invitado();
        invitado.setAsistenciaConfirmada(false);
        when(invitadoRepository.findById(1L)).thenReturn(Optional.of(invitado));
        when(invitadoRepository.save(invitado)).thenReturn(invitado);

        Invitado resultado = invitadoService.confirmarAsistencia(1L);
        assertTrue(resultado.getAsistenciaConfirmada());
        verify(invitadoRepository, times(1)).findById(1L);
        verify(invitadoRepository, times(1)).save(invitado);
    }

    @Test
    public void testConfirmarAsistencia_InvitadoNoExiste() {
        when(invitadoRepository.findById(1L)).thenReturn(Optional.empty());

        Invitado resultado = invitadoService.confirmarAsistencia(1L);
        assertNull(resultado);
        verify(invitadoRepository, times(1)).findById(1L);
        verify(invitadoRepository, never()).save(any());
    }
}
