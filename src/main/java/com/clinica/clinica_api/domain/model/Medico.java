package com.clinica.clinica_api.domain.model;

public class Medico extends Persona {

    
    private String especialidad; //atributo propio de medico

    
    public Medico(        //constructor de medico
        String nombre,
        String documento,
        int edad,
        String especialidad
    ) {

        super(nombre, documento, edad);        //llamamos a los atributos de la clase padre.

        this.especialidad = especialidad;      //guardamos la expecialidad.
    }

  
    public String getEspecialidad() {          //getter para obtener la expecialidad.
        return especialidad;
    }

    
    public void setEspecialidad(String especialidad) {      //setter para modificar la expecialidad
        this.especialidad = especialidad;
    }

    
    @Override                                       //sobre escribimos el método abstracto de la clase padre Persona para mostrar la información del medico.
    public void mostrarInformacion() {

        System.out.println("MEDICO");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Documento: " + getDocumento());
        System.out.println("Edad: " + getEdad());
        System.out.println("Especialidad: " + especialidad);
    }
}