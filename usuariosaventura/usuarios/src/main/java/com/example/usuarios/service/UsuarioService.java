package com.example.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.usuarios.model.Rol;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.RoleRepository;
import com.example.usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    //metodo para buscar todos los usuarios 
    public List<Usuario> obtenerusuarios(){
        return usuarioRepository.findAll();
    }

    //metodo para agregar nuevo usuario
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(String user,String pass, Long idRol){
        Rol role = roleRepository.findById(idRol)
        .orElseThrow(()-> new RuntimeException("Rol no existe con ID:"+ idRol));

        Usuario usuario1 = new Usuario();
        usuario1.setUsername(user);
        usuario1.setPassword(passwordEncoder.encode(pass));
        usuario1.setRol(role);
        return usuarioRepository.save(usuario1);
    }

    public Usuario modificarUsuario(Long id, String username, Long roleId) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuario.setUsername(username);
        if (roleId != null) {
            Rol role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no existe con ID: " + roleId));
            usuario.setRol(role);
        }
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuarioRepository.delete(usuario);
    }

    public Usuario iniciarSesion(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null || !passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return usuario;
    }
}
