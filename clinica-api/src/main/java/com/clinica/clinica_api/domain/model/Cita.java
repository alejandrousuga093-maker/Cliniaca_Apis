package com.clinica.clinica_api.domain.model;

public class Cita {

    // Una cita tiene un paciente.
    private Paciente paciente;

    // Una cita tiene un médico.
    private Medico medico;

    // Datos propios de la cita.
    private String fecha;
    private String hora;
    private String estado;

    // Constructor principal de Cita.
    public Cita(
        Paciente paciente,
        Medico medico,
        String fecha,
        String hora
    ) {

        // Guardamos el paciente.
        this.paciente = paciente;

        // Guardamos el médico.
        this.medico = medico;

        // Guardamos la fecha.
        this.fecha = fecha;

        // Guardamos la hora.
        this.hora = hora;

        // Toda cita nueva comienza como "Programada".
        this.estado = "Programada";
    }

    // Segundo constructor.
    // Esto demuestra SOBRECARGA porque tiene
    // los mismos nombres pero diferentes parámetros.
    public Cita(
        Paciente paciente,
        Medico medico,
        String fecha,
        String hora,
        String estado
    ) {

        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    // Getter del paciente.
    public Paciente getPaciente() {
        return paciente;
    }

    // Getter del médico.
    public Medico getMedico() {
        return medico;
    }

    // Getter de la fecha.
    public String getFecha() {
        return fecha;
    }

    // Setter de la fecha.
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Getter de la hora.
    public String getHora() {
        return hora;
    }

    // Setter de la hora.
    public void setHora(String hora) {
        this.hora = hora;
    }

    // Getter del estado.
    public String getEstado() {
        return estado;
    }

    // Método para cancelar una cita.
    // Recibe como parámetro el motivo.
    public void cancelar(String motivo) {

        this.estado = "Cancelada";

        System.out.println("Cita cancelada.");
        System.out.println("Motivo: " + motivo);
    }

    // Método para mostrar la información de la cita.
    public void mostrarCita() {

        System.out.println("CITA");
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Médico: " + medico.getNombre());
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Estado: " + estado);
    }
}
