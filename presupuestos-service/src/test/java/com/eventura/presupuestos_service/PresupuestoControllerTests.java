package com.eventura.presupuestos_service;

import com.eventura.presupuestos.controller.PresupuestoController;
import com.eventura.presupuestos.model.Presupuesto;
import com.eventura.presupuestos.service.PresupuestoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PresupuestoControllerTests {

    private MockMvc mockMvc;

    @Mock
    private PresupuestoService presupuestoService;

    @InjectMocks
    private PresupuestoController presupuestoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(presupuestoController).build();
    }

    @Test
    public void testRegistrar() throws Exception {
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setId(1L);
        presupuesto.setEventoId(1L);
        presupuesto.setDescripcion("Test presupuesto");

        when(presupuestoService.guardar(any(Presupuesto.class))).thenReturn(presupuesto);

        mockMvc.perform(post("/api/presupuestos/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"eventoId\":1,\"descripcion\":\"Test presupuesto\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descripcion").value("Test presupuesto"));
    }

    @Test
    public void testListar() throws Exception {
        Presupuesto p1 = new Presupuesto();
        p1.setId(1L);
        p1.setEventoId(1L);
        p1.setDescripcion("Presupuesto 1");

        Presupuesto p2 = new Presupuesto();
        p2.setId(2L);
        p2.setEventoId(1L);
        p2.setDescripcion("Presupuesto 2");

        List<Presupuesto> presupuestos = Arrays.asList(p1, p2);

        when(presupuestoService.listarPorEvento(1L)).thenReturn(presupuestos);

        mockMvc.perform(get("/api/presupuestos/evento/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void testTotal() throws Exception {
        when(presupuestoService.totalGastado(1L)).thenReturn(100.0);

        mockMvc.perform(get("/api/presupuestos/total/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    public void testModificar() throws Exception {
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setId(1L);
        presupuesto.setEventoId(1L);
        presupuesto.setDescripcion("Presupuesto modificado");

        when(presupuestoService.guardar(any(Presupuesto.class))).thenReturn(presupuesto);

        mockMvc.perform(put("/api/presupuestos/modificar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"eventoId\":1,\"descripcion\":\"Presupuesto modificado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Presupuesto modificado"));
    }
}
