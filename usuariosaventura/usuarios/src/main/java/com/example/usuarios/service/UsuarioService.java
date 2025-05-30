package com.example.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Usuario crearUsuario(String user,String pass, Long idRol){
        Rol role = roleRepository.findById(idRol)
        .orElseThrow(()-> new RuntimeException("Rol no existe con ID:"+ idRol));

        Usuario usuario1 = new Usuario();
        usuario1.setUsername(user);
        usuario1.setPassword(pass);
        usuario1.setRol(role);
        return usuarioRepository.save(usuario1);

        
    }


    

}
