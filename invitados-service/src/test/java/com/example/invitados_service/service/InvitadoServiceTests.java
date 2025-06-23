package com.example.invitados_service.service;

import com.example.invitados_service.model.Invitado;
import com.example.invitados_service.repository.InvitadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void testRegistrarInvitado() {
        Invitado invitado = new Invitado();
        invitado.setNombre("Juan");
        invitado.setEmail("juan@example.com");
        invitado.setPassword("123456");

        when(invitadoRepository.save(any(Invitado.class))).thenReturn(invitado);

        Invitado resultado = invitadoService.registrarInvitado(invitado);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(invitadoRepository, times(1)).save(invitado);
    }

    @Test
    public void testIniciarSesion_Exitoso() {
        Invitado invitado = new Invitado();
        invitado.setEmail("juan@example.com");
        invitado.setPassword("123456");

        when(invitadoRepository.findByEmail("juan@example.com"))
                .thenReturn(Optional.of(invitado));

        Optional<Invitado> resultado = invitadoService.iniciarSesion("juan@example.com", "123456");

        assertTrue(resultado.isPresent());
        assertEquals("juan@example.com", resultado.get().getEmail());
    }

    @Test
    public void testIniciarSesion_Fallido() {
        when(invitadoRepository.findByEmail("juan@example.com"))
                .thenReturn(Optional.of(new Invitado()));

        Optional<Invitado> resultado = invitadoService.iniciarSesion("juan@example.com", "wrongpass");

        assertFalse(resultado.isPresent());
    }

}
