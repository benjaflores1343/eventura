package com.eventura.presupuestos.controller;

import com.eventura.presupuestos.model.Presupuesto;
import com.eventura.presupuestos.service.PresupuestoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PresupuestoController.class)
public class PresupuestoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PresupuestoService presupuestoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Presupuesto presupuesto;

    @BeforeEach
    public void setUp() {
        presupuesto = new Presupuesto();
        presupuesto.setId(1L);
        presupuesto.setMonto(100.0);
    }

    @Test
    public void testRegistrar() throws Exception {
        Mockito.when(presupuestoService.guardar(any(Presupuesto.class))).thenReturn(presupuesto);

        mockMvc.perform(post("/api/presupuestos/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(presupuesto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.monto").value(100.0));
    }

    @Test
    public void testRegistrar_NullBody() throws Exception {
        mockMvc.perform(post("/api/presupuestos/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testListarPorEvento() throws Exception {
        List<Presupuesto> lista = Arrays.asList(presupuesto);
        Mockito.when(presupuestoService.listarPorEvento(1L)).thenReturn(lista);

        mockMvc.perform(get("/api/presupuestos/evento/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testListarPorEvento_NullEventoId() throws Exception {
        mockMvc.perform(get("/api/presupuestos/evento/null"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testListarPorEventoYUsuario() throws Exception {
        List<Presupuesto> lista = Arrays.asList(presupuesto);
        Mockito.when(presupuestoService.listarPorEventoYUsuario(1L, 2L)).thenReturn(lista);

        mockMvc.perform(get("/api/presupuestos/evento/1")
                .param("usuarioId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    public void testTotalPorEvento() throws Exception {
        Mockito.when(presupuestoService.totalGastado(1L)).thenReturn(100.0);

        mockMvc.perform(get("/api/presupuestos/total/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    public void testTotalPorEventoYUsuario() throws Exception {
        Mockito.when(presupuestoService.totalGastado(1L, 2L)).thenReturn(100.0);

        mockMvc.perform(get("/api/presupuestos/total/1")
                .param("usuarioId", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    public void testModificar() throws Exception {
        Mockito.when(presupuestoService.guardar(any(Presupuesto.class))).thenReturn(presupuesto);

        mockMvc.perform(put("/api/presupuestos/modificar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(presupuesto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
