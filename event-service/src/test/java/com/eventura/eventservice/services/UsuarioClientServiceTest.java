package com.eventura.eventservice.services;

import com.eventura.eventservice.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para UsuarioClientService.
 */
public class UsuarioClientServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UsuarioClientService usuarioClientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioClientService.setUsuarioServiceUrl("http://localhost:8081");
    }

    @Test
    public void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Usuario Test");
        usuario.setEmail("usuario@test.com");

        when(restTemplate.getForObject("http://localhost:8081/usuarios/1", Usuario.class)).thenReturn(usuario);

        Usuario result = usuarioClientService.getUsuarioById(1L);

        assertNotNull(result);
        assertEquals("Usuario Test", result.getNombre());
        assertEquals("usuario@test.com", result.getEmail());
        verify(restTemplate, times(1)).getForObject("http://localhost:8081/usuarios/1", Usuario.class);
    }

    @Test
    public void testGetUsuarioById_NotFound() {
        when(restTemplate.getForObject("http://localhost:8081/usuarios/2", Usuario.class)).thenReturn(null);

        Usuario result = usuarioClientService.getUsuarioById(2L);

        assertNull(result);
        verify(restTemplate, times(1)).getForObject("http://localhost:8081/usuarios/2", Usuario.class);
    }

    @Test
    public void testSetUsuarioServiceUrl() {
        usuarioClientService.setUsuarioServiceUrl("http://new-url:8082");
        assertEquals("http://new-url:8082", usuarioClientService.getUsuarioServiceUrl());
    }
}
