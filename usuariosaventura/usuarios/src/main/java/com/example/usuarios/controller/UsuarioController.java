package com.example.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.model.Rol;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.RoleService;
import com.example.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/v1")

public class UsuarioController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/users")

    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios(){
        List<Usuario> users = usuarioService.obtenerusuarios();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(users);
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> obtenerRoles(){
        List<Rol> roles = roleService.obtenerRoles();
        if(roles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }



    @PostMapping("/users")
    public ResponseEntity<?> crearUsuario(@RequestParam String username, @RequestParam String password, @RequestParam Long roleId){

        try {
            Usuario usernuevo = usuarioService.crearUsuario(username, password, roleId);
            return ResponseEntity.status(HttpStatus.CREATED).body(usernuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestParam String username, @RequestParam(required = false) Long roleId) {
        try {
            Usuario usuarioModificado = usuarioService.modificarUsuario(id, username, roleId);
            return ResponseEntity.ok(usuarioModificado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestParam String username, @RequestParam String password) {
        try {
            Usuario usuario = usuarioService.iniciarSesion(username, password);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
