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

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class InvitadoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InvitadoService invitadoService;

    @InjectMocks
    private InvitadoController invitadoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invitadoController).build();
    }

    @Test
    public void testRegistrar() throws Exception {
        Invitado invitado = new Invitado();
        when(invitadoService.guardar(any(Invitado.class))).thenReturn(invitado);

        mockMvc.perform(post("/api/invitados/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListar() throws Exception {
        when(invitadoService.listarPorEvento(any(Long.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/invitados/evento/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
