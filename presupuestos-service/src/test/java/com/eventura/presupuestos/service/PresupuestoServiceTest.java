package com.eventura.presupuestos.service;

import com.eventura.presupuestos.model.Presupuesto;
import com.eventura.presupuestos.repository.PresupuestoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PresupuestoServiceTest {

    @Mock
    private PresupuestoRepository presupuestoRepository;

    @InjectMocks
    private PresupuestoService presupuestoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Presupuesto presupuesto = new Presupuesto();
        when(presupuestoRepository.save(presupuesto)).thenReturn(presupuesto);

        Presupuesto resultado = presupuestoService.guardar(presupuesto);
        assertNotNull(resultado);
        verify(presupuestoRepository, times(1)).save(presupuesto);
    }

    @Test
    public void testListarPorEvento() {
        Long eventoId = 1L;
        List<Presupuesto> listaMock = Arrays.asList(new Presupuesto(), new Presupuesto());
        when(presupuestoRepository.findByEventoId(eventoId)).thenReturn(listaMock);

        List<Presupuesto> resultado = presupuestoService.listarPorEvento(eventoId);
        assertEquals(2, resultado.size());
        verify(presupuestoRepository, times(1)).findByEventoId(eventoId);
    }

    @Test
    public void testListarPorEventoYUsuario() {
        Long eventoId = 1L;
        Long usuarioId = 2L;
        List<Presupuesto> listaMock = Arrays.asList(new Presupuesto());
        when(presupuestoRepository.findByEventoIdAndUsuarioId(eventoId, usuarioId)).thenReturn(listaMock);

        List<Presupuesto> resultado = presupuestoService.listarPorEventoYUsuario(eventoId, usuarioId);
        assertEquals(1, resultado.size());
        verify(presupuestoRepository, times(1)).findByEventoIdAndUsuarioId(eventoId, usuarioId);
    }

    @Test
    public void testTotalGastadoPorEvento() {
        Long eventoId = 1L;
        Presupuesto p1 = new Presupuesto();
        p1.setMonto(100.0);
        Presupuesto p2 = new Presupuesto();
        p2.setMonto(200.0);
        List<Presupuesto> listaMock = Arrays.asList(p1, p2);
        when(presupuestoRepository.findByEventoId(eventoId)).thenReturn(listaMock);

        Double total = presupuestoService.totalGastado(eventoId);
        assertEquals(300.0, total);
        verify(presupuestoRepository, times(1)).findByEventoId(eventoId);
    }

    @Test
    public void testTotalGastadoPorEventoYUsuario() {
        Long eventoId = 1L;
        Long usuarioId = 2L;
        Presupuesto p1 = new Presupuesto();
        p1.setMonto(150.0);
        List<Presupuesto> listaMock = Arrays.asList(p1);
        when(presupuestoRepository.findByEventoIdAndUsuarioId(eventoId, usuarioId)).thenReturn(listaMock);

        Double total = presupuestoService.totalGastado(eventoId, usuarioId);
        assertEquals(150.0, total);
        verify(presupuestoRepository, times(1)).findByEventoIdAndUsuarioId(eventoId, usuarioId);
    }
}
