package com.example.usuarios.controller;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para pruebas de creación de usuario y verificación de contraseña encriptada.
 */
@RestController
@RequestMapping("/api/test/usuarios")
public class UsuarioTestController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestParam String username, @RequestParam String password, @RequestParam Long roleId) {
        return usuarioService.crearUsuario(username, password, roleId);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerusuarios()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
