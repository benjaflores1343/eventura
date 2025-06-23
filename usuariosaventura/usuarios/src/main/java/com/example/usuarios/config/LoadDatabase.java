package com.example.usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.usuarios.model.Rol;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.RoleRepository;
import com.example.usuarios.repository.UsuarioRepository;

 

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepo, UsuarioRepository userRepo, PasswordEncoder passwordEncoder){
        return args->{
            // Limpiar tablas para forzar recarga
            userRepo.deleteAll();
            roleRepo.deleteAll();

            //cargar los roles por defecto 
            Rol admin = new Rol();
            admin.setNombre("administrador");
            roleRepo.save(admin);

            Rol user = new Rol();
            user.setNombre("Usuario");
            roleRepo.save(user);

            //cargar usuarios por defecto o iniciales
            String encodedPassword1 = passwordEncoder.encode("123456");
            System.out.println("Encoded password for benjamin: " + encodedPassword1);
            userRepo.save(new Usuario(null,"benjamin", encodedPassword1, admin));

            String encodedPassword2 = passwordEncoder.encode("123456");
            System.out.println("Encoded password for alex: " + encodedPassword2);
            userRepo.save(new Usuario(null,"alex", encodedPassword2, user));
           
            System.out.println("Datos iniciales Cargados");


        };


    }
}
