package com.clinica.clinica_api.presentation.dto;

import com.clinica.clinica_api.domain.model.Cita;

public class CitaResponse {
    private String id;
    private String nombrePaciente;
    private String documentoPaciente;
    private String nombreMedico;
    private String documentoMedico;
    private String especialidad;
    private String fecha;
    private String hora;
    private String estado;
    private String motivo;

    public CitaResponse(Cita cita) {
        this.id = cita.getId();
        this.nombrePaciente = cita.getPaciente().getNombre();
        this.documentoPaciente = cita.getPaciente().getDocumento();
        this.nombreMedico = cita.getMedico().getNombre();
        this.documentoMedico = cita.getMedico().getDocumento();
        this.especialidad = cita.getMedico().getEspecialidad();
        this.fecha = cita.getFecha();
        this.hora = cita.getHora();
        this.estado = cita.getEstado();
        this.motivo = cita.getMotivo();
    }

    public String getId() { return id; }
    public String getNombrePaciente() { return nombrePaciente; }
    public String getDocumentoPaciente() { return documentoPaciente; }
    public String getNombreMedico() { return nombreMedico; }
    public String getDocumentoMedico() { return documentoMedico; }
    public String getEspecialidad() { return especialidad; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getEstado() { return estado; }
    public String getMotivo() { return motivo; }
}