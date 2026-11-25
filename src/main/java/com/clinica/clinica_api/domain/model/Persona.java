package com.clinica.clinica_api.domain.model;

public abstract class Persona {

    // Atributos privados para aplicar encapsulamiento.
    private String nombre;
    private String documento;
    private int edad;

    
    private static int totalPersonas = 0; // Atributo static para contar el total de personas creadas que solo pertenece a la clase Persona y no a cada objeto.

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

    
    public abstract void mostrarInformacion(); // Método abstracto que obliga a las clases hijas a implementar su propia versión de mostrar información.
}
