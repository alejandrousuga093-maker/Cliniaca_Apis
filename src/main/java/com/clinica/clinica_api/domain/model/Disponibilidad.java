package com.clinica.clinica_api.domain.model;

import java.util.UUID;

public class Disponibilidad {
    private String id;
    private String documentoMedico;
    private String fecha;
    private String horaInicio;
    private String horaFin;

    public Disponibilidad() {}

    public Disponibilidad(String documentoMedico, String fecha, String horaInicio, String horaFin) {
        if (horaFin.compareTo(horaInicio) <= 0) {
            throw new IllegalArgumentException("La hora final debe ser mayor que la hora inicial.");
        }
        this.id = "disp-" + UUID.randomUUID().toString().substring(0, 6);
        this.documentoMedico = documentoMedico;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getId() { return id; }
    public String getDocumentoMedico() { return documentoMedico; }
    public void setDocumentoMedico(String documentoMedico) { this.documentoMedico = documentoMedico; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
}