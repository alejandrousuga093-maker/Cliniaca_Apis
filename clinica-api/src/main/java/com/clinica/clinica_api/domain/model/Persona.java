package com.clinica.clinica_api.domain.model;

public abstract class Persona {

    // Atributos privados para aplicar encapsulamiento.
    private String nombre;
    private String documento;
    private int edad;

    // Variable static.
    // Pertenece a la clase y no a un objeto específico.
    // Nos permite contar cuántas personas se han creado.
    private static int totalPersonas = 0;

    // Constructor de Persona.
    public Persona(String nombre, String documento, int edad) {

        // Guardamos los valores recibidos en los atributos.
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;

        // Aumentamos el contador cada vez que se crea una persona.
        totalPersonas++;
    }

    // Getter para obtener el nombre.
    public String getNombre() {
        return nombre;
    }

    // Setter para modificar el nombre.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para obtener el documento.
    public String getDocumento() {
        return documento;
    }

    // Setter para modificar el documento.
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    // Getter para obtener la edad.
    public int getEdad() {
        return edad;
    }

    // Setter para modificar la edad.
    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Método static para consultar el total de personas.
    public static int getTotalPersonas() {
        return totalPersonas;
    }

    // Método abstracto.
    // Las clases hijas tendrán que definir cómo mostrar la información.
    public abstract void mostrarInformacion();
}
