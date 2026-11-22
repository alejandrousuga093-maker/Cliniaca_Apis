package com.clinica.clinica_api.domain.model;

public class Recepcionista extends Persona {

    private String turno;

    public Recepcionista() {
        super();
    }

    // Constructor original intacto
    public Recepcionista(String nombre, String documento, int edad, String turno) {
        super(nombre, documento, edad);
        this.turno = turno;
    }

    // Constructor sobrecargado completo
    public Recepcionista(String nombre, String documento, int edad, String turno, String correo, String password, String telefono) {
        super(nombre, documento, edad, correo, password, telefono);
        this.turno = turno;
    }

    @Override
    public String obtenerRol() {
        return "ADMINISTRADOR";
    }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public void mostrarInformacion() {
        System.out.println("RECEPCIONISTA / ADMIN: " + getNombre() + " | Turno: " + turno);
    }
}