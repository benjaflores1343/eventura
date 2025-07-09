package com.eventura.tareas_service.controller;

import com.eventura.tareas_service.model.Tarea;
import com.eventura.tareas_service.service.TareaService;
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

public class TareaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TareaService tareaService;

    @InjectMocks
    private TareaController tareaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tareaController).build();
    }

    @Test
    public void testRegistrar() throws Exception {
        Tarea tarea = new Tarea();
        when(tareaService.guardar(any(Tarea.class))).thenReturn(tarea);

        mockMvc.perform(post("/api/tareas/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListar() throws Exception {
        when(tareaService.listarPorEvento(any(Long.class))).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/tareas/evento/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testCompletar() throws Exception {
        Tarea tarea = new Tarea();
        when(tareaService.marcarComoCompletada(any(Long.class))).thenReturn(tarea);

        mockMvc.perform(put("/api/tareas/completar/1"))
                .andExpect(status().isOk());
    }
}
