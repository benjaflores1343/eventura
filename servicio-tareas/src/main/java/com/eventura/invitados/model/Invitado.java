package com.eventura.invitados.model;

import jakarta.persistence.*;

@Entity
public class Invitado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private Boolean asistenciaConfirmada;
    private Integer numeroAcompanantes;
    private Long eventoId;

    // Getters y setters
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

    public Boolean getAsistenciaConfirmada() {
        return asistenciaConfirmada;
    }

    public void setAsistenciaConfirmada(Boolean asistenciaConfirmada) {
        this.asistenciaConfirmada = asistenciaConfirmada;
    }

    public Integer getNumeroAcompanantes() {
        return numeroAcompanantes;
    }

    public void setNumeroAcompanantes(Integer numeroAcompanantes) {
        this.numeroAcompanantes = numeroAcompanantes;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }
}
