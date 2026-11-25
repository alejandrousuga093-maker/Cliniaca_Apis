package com.clinica.clinica_api.domain.model;

public class Paciente extends Persona {

    
    private String nivelRiesgo;  //atribustos propios de paciente 
    private int citasIncumplidas;

    
    public Paciente(
        String nombre,            // Atributo heredado de Persona.
        String documento,
        int edad,
        String nivelRiesgo
    ) {

        super(nombre, documento, edad); // Llamamos al constructor de la clase padre (Persona) para inicializar los atributos heredados.
        this.nivelRiesgo = nivelRiesgo;
        this.citasIncumplidas = 0;
    }

   
    public String getNivelRiesgo() { // Getter del nivel de riesgo.
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {  // Setter del nivel de riesgo.
        this.nivelRiesgo = nivelRiesgo;
    }

    
    public int getCitasIncumplidas() {  // Getter del número de citas incumplidas.
        return citasIncumplidas;
    }

    
    public void registrarCitaIncumplida() { // Método para registrar una cita incumplida.

        citasIncumplidas++; // Incrementamos el contador de citas incumplidas en 1.
    }

    
    public boolean necesitaSeguimiento() { // para indetificar si el paciente necesita seguimiento segun la regla del proyecto.


        if (citasIncumplidas >= 2) {  //si tiene dos o mas citas imcumplidas necesista seguimiento y retorna true, de lo contrario retorna false.
            return true;
        } else {
            return false;
        }
    }

   
    @Override                                      //sobre escribimos el método abstracto de la clase padre Persona para mostrar la información del paciente.
    public void mostrarInformacion() {

        System.out.println("PACIENTE ");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Documento: " + getDocumento());
        System.out.println("Edad: " + getEdad());
        System.out.println("Nivel de riesgo: " + nivelRiesgo);
        System.out.println("Citas incumplidas: " + citasIncumplidas);
    }
}