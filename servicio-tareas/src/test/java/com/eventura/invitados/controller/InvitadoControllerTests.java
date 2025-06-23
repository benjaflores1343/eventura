package com.eventura.invitados.controller;

import com.eventura.invitados.model.Invitado;
import com.eventura.invitados.service.InvitadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InvitadoControllerTests {

    private MockMvc mockMvc;

    @Mock
    private InvitadoService invitadoService;

    @InjectMocks
    private InvitadoController invitadoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invitadoController).build();
    }

    @Test
    public void testRegistrar() throws Exception {
        Invitado invitado = new Invitado();
        when(invitadoService.guardar(any(Invitado.class))).thenReturn(invitado);

        mockMvc.perform(post("/api/invitados/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invitado)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(invitado)));

        verify(invitadoService, times(1)).guardar(any(Invitado.class));
    }

    @Test
    public void testListar() throws Exception {
        List<Invitado> invitados = List.of(new Invitado());
        when(invitadoService.listarPorEvento(1L)).thenReturn(invitados);

        mockMvc.perform(get("/api/invitados/evento/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(invitados)));

        verify(invitadoService, times(1)).listarPorEvento(1L);
    }

    @Test
    public void testConfirmar() throws Exception {
        Invitado invitado = new Invitado();
        when(invitadoService.confirmarAsistencia(1L)).thenReturn(invitado);

        mockMvc.perform(put("/api/invitados/confirmar/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(invitado)));

        verify(invitadoService, times(1)).confirmarAsistencia(1L);
    }
}
