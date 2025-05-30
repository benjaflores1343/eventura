package com.example.invitados_service.model;

import jakarta.persistence.*;

@Entity
public class Invitado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String password;

    private boolean asistenciaConfirmada;

    private String restriccionesAlimentarias;

    private String mensajeAnfitrion;

    private int numeroAcompanantes;

    @ElementCollection
    private java.util.List<String> nombresAcompanantes;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAsistenciaConfirmada() {
        return asistenciaConfirmada;
    }

    public void setAsistenciaConfirmada(boolean asistenciaConfirmada) {
        this.asistenciaConfirmada = asistenciaConfirmada;
    }

    public String getRestriccionesAlimentarias() {
        return restriccionesAlimentarias;
    }

    public void setRestriccionesAlimentarias(String restriccionesAlimentarias) {
        this.restriccionesAlimentarias = restriccionesAlimentarias;
    }

    public String getMensajeAnfitrion() {
        return mensajeAnfitrion;
    }

    public void setMensajeAnfitrion(String mensajeAnfitrion) {
        this.mensajeAnfitrion = mensajeAnfitrion;
    }

    public int getNumeroAcompanantes() {
        return numeroAcompanantes;
    }

    public void setNumeroAcompanantes(int numeroAcompanantes) {
        this.numeroAcompanantes = numeroAcompanantes;
    }

    public java.util.List<String> getNombresAcompanantes() {
        return nombresAcompanantes;
    }

    public void setNombresAcompanantes(java.util.List<String> nombresAcompanantes) {
        this.nombresAcompanantes = nombresAcompanantes;
    }
}
