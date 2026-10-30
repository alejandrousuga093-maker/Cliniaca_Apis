package com.clinica.clinica_api.domain.model;

public class Paciente extends Persona {

    // Atributos propios del paciente.
    private String nivelRiesgo;
    private int citasIncumplidas;

    // Constructor del paciente.
    public Paciente(
        String nombre,
        String documento,
        int edad,
        String nivelRiesgo
    ) {

        // Llamamos al constructor de Persona.
        super(nombre, documento, edad);

        // Guardamos el nivel de riesgo.
        this.nivelRiesgo = nivelRiesgo;

        // Al crear un paciente empieza con cero
        // citas incumplidas.
        this.citasIncumplidas = 0;
    }

    // Getter del nivel de riesgo.
    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    // Setter del nivel de riesgo.
    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    // Getter de las citas incumplidas.
    public int getCitasIncumplidas() {
        return citasIncumplidas;
    }

    // Método para registrar una cita incumplida.
    public void registrarCitaIncumplida() {

        // Aumentamos el contador en uno.
        citasIncumplidas++;
    }

    // Método que determina si el paciente necesita seguimiento.
    public boolean necesitaSeguimiento() {

        // Según la regla del proyecto:
        // si tiene 2 o más citas incumplidas,
        // necesita seguimiento.
        if (citasIncumplidas >= 2) {
            return true;
        } else {
            return false;
        }
    }

    // Sobreescribimos el método de Persona.
    @Override
    public void mostrarInformacion() {

        System.out.println("PACIENTE ");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Documento: " + getDocumento());
        System.out.println("Edad: " + getEdad());
        System.out.println("Nivel de riesgo: " + nivelRiesgo);
        System.out.println("Citas incumplidas: " + citasIncumplidas);
    }
}