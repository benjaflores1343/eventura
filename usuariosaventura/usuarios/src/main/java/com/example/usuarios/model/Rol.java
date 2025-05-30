package com.example.usuarios.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Rol {

    @Id //identificador primario
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrementa
    private Long id;

    @Column(nullable = false, unique = true, length = 50) //obligatorio, unico y de longitud 50 máximo
    private String nombre;

    //identificar la relacion existente con la tabla

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    @JsonIgnore  //no se incluyan los usuarios cuando consulte los roles
    private List<Usuario> users;//lsita porque el pol puede tener mas de un usuario

    


}
