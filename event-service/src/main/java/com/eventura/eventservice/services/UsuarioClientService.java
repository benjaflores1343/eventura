package com.eventura.eventservice.services;

import com.eventura.eventservice.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Servicio cliente para comunicarse con el microservicio de usuarios.
 */
@Service
public class UsuarioClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${usuarioservice.url}")
    private String usuarioServiceUrl;

    // Para pruebas, permitir setear la URL
    public void setUsuarioServiceUrl(String usuarioServiceUrl) {
        this.usuarioServiceUrl = usuarioServiceUrl;
    }

    public String getUsuarioServiceUrl() {
        return this.usuarioServiceUrl;
    }

    public Usuario getUsuarioById(Long id) {
        String url = usuarioServiceUrl + "/usuarios/" + id;
        return restTemplate.getForObject(url, Usuario.class);
    }
}
