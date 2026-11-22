package com.clinica.clinica_api.domain.model;

import java.util.UUID;

public class Cita {

    private String id;
    private Paciente paciente;
    private Medico medico;
    private String fecha;
    private String hora;
    private String estado;
    private String motivo;

    // Constructor original 1 intacto
    public Cita(Paciente paciente, Medico medico, String fecha, String hora) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "Programada";
        this.motivo = "Consulta médica general";
    }

    // Constructor original 2 sobrecargado intacto
    public Cita(Paciente paciente, Medico medico, String fecha, String hora, String estado) {
        this(paciente, medico, fecha, hora);
        this.estado = estado;
    }

    // Constructor sobrecargado con motivo
    public Cita(Paciente paciente, Medico medico, String fecha, String hora, String estado, String motivo) {
        this(paciente, medico, fecha, hora, estado);
        if (motivo != null && !motivo.isBlank()) this.motivo = motivo;
    }

    // ENCAPSULAMIENTO: Reglas de transición de estado dentro del objeto
    public void cancelar(String motivo) {
        if ("Atendida".equalsIgnoreCase(this.estado) || "Cancelada".equalsIgnoreCase(this.estado)) {
            throw new IllegalStateException("No es posible cancelar una cita en estado: " + this.estado);
        }
        this.estado = "Cancelada";
        if (motivo != null && !motivo.isBlank()) {
            this.motivo = motivo;
        }
    }

    public void reprogramar(String nuevaFecha, String nuevaHora) {
        if ("Atendida".equalsIgnoreCase(this.estado) || "Cancelada".equalsIgnoreCase(this.estado)) {
            throw new IllegalStateException("Solo se pueden reprogramar citas activas.");
        }
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        this.estado = "Confirmada";
    }

    public void confirmar() {
        this.estado = "Confirmada";
    }

    public void marcarAtendida() {
        if ("Cancelada".equalsIgnoreCase(this.estado)) {
            throw new IllegalStateException("No se puede atender una cita cancelada.");
        }
        this.estado = "Atendida";
    }

    public void marcarIncumplida() {
        this.estado = "Incumplida";
        if (this.paciente != null) {
            this.paciente.registrarCitaIncumplida();
        }
    }

    public void mostrarCita() {
        System.out.println("CITA #" + id + " | " + paciente.getNombre() + " con " + medico.getNombre() + " (" + fecha + " " + hora + ")");
    }

    // Getters y Setters
    public String getId() { return id; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getEstado() { return estado; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}