package com.clinica.clinica_api.domain.model;

public class Medico extends Persona {

    private String especialidad;
    private String registroMedico;
    private String consultorio;

    public Medico() {
        super();
    }

    // Constructor original intacto
    public Medico(String nombre, String documento, int edad, String especialidad) {
        super(nombre, documento, edad);
        this.especialidad = especialidad;
        this.registroMedico = "RM-" + documento;
        this.consultorio = "Consultorio General";
    }

    // Constructor sobrecargado completo
    public Medico(String nombre, String documento, int edad, String especialidad, String registroMedico, String consultorio, String correo, String password, String telefono) {
        super(nombre, documento, edad, correo, password, telefono);
        this.especialidad = especialidad;
        this.registroMedico = registroMedico;
        this.consultorio = consultorio;
    }

    @Override
    public String obtenerRol() {
        return "MEDICO";
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getRegistroMedico() { return registroMedico; }
    public void setRegistroMedico(String registroMedico) { this.registroMedico = registroMedico; }

    public String getConsultorio() { return consultorio; }
    public void setConsultorio(String consultorio) { this.consultorio = consultorio; }

    @Override
    public void mostrarInformacion() {
        System.out.println("MEDICO: " + getNombre() + " | Especialidad: " + especialidad);
    }
}