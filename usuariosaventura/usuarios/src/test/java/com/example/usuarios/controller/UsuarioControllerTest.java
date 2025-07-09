package com.example.usuarios.controller;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.UsuarioService;
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

public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void testRegistrar() throws Exception {
        Usuario usuario = new Usuario();
        when(usuarioService.crearUsuario(any(String.class), any(String.class), any(Long.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListar() throws Exception {
        when(usuarioService.obtenerusuarios()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/usuarios/evento/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
