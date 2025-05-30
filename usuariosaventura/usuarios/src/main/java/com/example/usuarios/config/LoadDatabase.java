package com.example.usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.usuarios.model.Rol;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.RoleRepository;
import com.example.usuarios.repository.UsuarioRepository;

 

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepo, UsuarioRepository userRepo){
        return args->{
            //si las tablas estan vacias - no hay registros
            if(roleRepo.count() == 0 && userRepo.count() ==0){
                //cargar los roles por defecto 
                Rol admin = new Rol();
                admin.setNombre("administrador");
                roleRepo.save(admin);


                Rol user = new Rol();
                user.setNombre("Usuario");
                roleRepo.save(user);



                //cargar usuarios por defecto o iniciales
                userRepo.save(new Usuario(null,"benjamin","123456", admin));
                userRepo.save(new Usuario(null,"alex","123456", user));
               

                System.out.println("Datos iniciales Cargados");

            }
            else{
                System.out.println("Datos ya existentes. No se cargaron nuevos datos");
            }


        };


    }
}
