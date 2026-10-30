package com.clinica.clinica_api.domain.model;

public class Medico extends Persona {

    // Atributo propio del médico.
    private String especialidad;

    // Constructor del médico.
    public Medico(
        String nombre,
        String documento,
        int edad,
        String especialidad
    ) {

        // Llamamos al constructor de Persona
        //sirve para llamar al constructor de la clase padre..
        super(nombre, documento, edad);

        // Guardamos la especialidad.
        this.especialidad = especialidad;
    }

    // Getter para obtener la especialidad.
    public String getEspecialidad() {
        return especialidad;
    }

    // Setter para modificar la especialidad.
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // Sobreescribimos el método abstracto de Persona.
    @Override
    public void mostrarInformacion() {

        System.out.println("MEDICO");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Documento: " + getDocumento());
        System.out.println("Edad: " + getEdad());
        System.out.println("Especialidad: " + especialidad);
    }
}