package com.eventura.soporte.controller;

import com.eventura.soporte.model.Soporte;
import com.eventura.soporte.service.SoporteService;
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

public class SoporteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SoporteService soporteService;

    @InjectMocks
    private SoporteController soporteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(soporteController).build();
    }

    @Test
    public void testRegistrar() throws Exception {
        Soporte soporte = new Soporte();
        when(soporteService.guardar(any(Soporte.class))).thenReturn(soporte);

        mockMvc.perform(post("/api/soporte/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListar() throws Exception {
        when(soporteService.listarPorEvento(any(Long.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/soporte/evento/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testCambiarEstado() throws Exception {
        Soporte soporte = new Soporte();
        when(soporteService.actualizarEstado(any(Long.class), any(String.class))).thenReturn(soporte);

        mockMvc.perform(put("/api/soporte/estado/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"activo\""))
                .andExpect(status().isOk());
    }
}
