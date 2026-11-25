package com.clinica.clinica_api.presentation.dto;

import com.clinica.clinica_api.domain.model.Paciente;

public class PacienteResponse {

    private String nombre;
    private String documento;
    private int edad;
    private String nivelRiesgo;
    private int citasIncumplidas;
    private boolean necesitaSeguimiento;

    public PacienteResponse(Paciente paciente) {
        this.nombre = paciente.getNombre();
        this.documento = paciente.getDocumento();
        this.edad = paciente.getEdad();
        this.nivelRiesgo = paciente.getNivelRiesgo();
        this.citasIncumplidas =
                paciente.getCitasIncumplidas();
        this.necesitaSeguimiento =
                paciente.necesitaSeguimiento();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public int getEdad() {
        return edad;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public int getCitasIncumplidas() {
        return citasIncumplidas;
    }

    public boolean isNecesitaSeguimiento() {
        return necesitaSeguimiento;
    }
}