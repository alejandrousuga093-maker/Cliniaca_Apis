package com.clinica.clinica_api.presentation.dto;

import com.clinica.clinica_api.domain.model.Cita;

public class CitaResponse {

    private String nombrePaciente;
    private String nombreMedico;
    private String fecha;
    private String hora;
    private String estado;

    public CitaResponse(Cita cita) {
        this.nombrePaciente =
                cita.getPaciente().getNombre();
        this.nombreMedico =
                cita.getMedico().getNombre();
        this.fecha = cita.getFecha();
        this.hora = cita.getHora();
        this.estado = cita.getEstado();
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }
}