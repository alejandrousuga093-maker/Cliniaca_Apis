package com.clinica.clinica_api.domain.model;

public class Recepcionista extends Persona {

    
    private String turno;

    // Constructor.
    public Recepcionista(
        String nombre,
        String documento,
        int edad,
        String turno
    ) {

        
        super(nombre, documento, edad);

        
        this.turno = turno;
    }

    
    public String getTurno() {
        return turno;
    }

    // Setter del turno.
    public void setTurno(String turno) {
        this.turno = turno;
    }

    // Sobreescribimos el método de Persona.
    @Override
    public void mostrarInformacion() {

        System.out.println(" RECEPCIONISTA ");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Documento: " + getDocumento());
        System.out.println("Edad: " + getEdad());
        System.out.println("Turno: " + turno);
    }
}