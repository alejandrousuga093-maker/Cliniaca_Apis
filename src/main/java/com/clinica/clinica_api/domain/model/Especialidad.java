package com.clinica.clinica_api.domain.model;

import java.util.UUID;

public class Especialidad {
    private String id;
    private String nombre;
    private String descripcion;
    private boolean activa;

    public Especialidad() {}

    public Especialidad(String nombre, String descripcion) {
        this.id = "esp-" + UUID.randomUUID().toString().substring(0, 6);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activa = true;
    }

    public void activar() { this.activa = true; }
    public void desactivar() { this.activa = false; }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}