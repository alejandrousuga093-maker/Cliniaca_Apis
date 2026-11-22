package com.clinica.clinica_api.domain.model;

public class Paciente extends Persona {

    private String nivelRiesgo = "Bajo";
    private int citasIncumplidas = 0;

    public Paciente() {
        super();
    }

    // Constructor original intacto para pruebas unitarias
    public Paciente(String nombre, String documento, int edad, String nivelRiesgo) {
        super(nombre, documento, edad);
        this.nivelRiesgo = nivelRiesgo;
        this.citasIncumplidas = 0;
    }

    // Constructor sobrecargado para registro completo
    public Paciente(String nombre, String documento, int edad, String nivelRiesgo, String correo, String password, String telefono) {
        super(nombre, documento, edad, correo, password, telefono);
        this.nivelRiesgo = (nivelRiesgo == null || nivelRiesgo.isBlank()) ? "Bajo" : nivelRiesgo;
        this.citasIncumplidas = 0;
    }

    @Override
    public String obtenerRol() {
        return "PACIENTE";
    }

    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }

    public int getCitasIncumplidas() { return citasIncumplidas; }

    public void registrarCitaIncumplida() {
        citasIncumplidas++;
        if (citasIncumplidas >= 2) {
            this.nivelRiesgo = "Alto";
        }
    }

    public boolean necesitaSeguimiento() {
        return citasIncumplidas >= 2;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("PACIENTE: " + getNombre() + " | Doc: " + getDocumento() + " | Riesgo: " + nivelRiesgo);
    }
}