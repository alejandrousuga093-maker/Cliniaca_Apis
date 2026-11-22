package com.clinica.clinica_api.domain.model;

public abstract class Persona {

    private String nombre;
    private String documento;
    private int edad;
    private String correo;
    private String password;
    private String telefono;
    private boolean activo = true;

    private static int totalPersonas = 0;

    // Constructor vacío requerido por Spring Boot para deserializar JSON
    protected Persona() {
    }

    // Constructor original (se mantiene intacto para tus 12 pruebas unitarias)
    public Persona(String nombre, String documento, int edad) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.correo = documento + "@medik.com";
        this.password = "123";
        this.telefono = "Sin registrar";
        this.activo = true;
        totalPersonas++;
    }

    // Constructor sobrecargado completo para el registro desde la web
    public Persona(String nombre, String documento, int edad, String correo, String password, String telefono) {
        this(nombre, documento, edad);
        if (correo != null && !correo.isBlank()) this.correo = correo.trim().toLowerCase();
        if (password != null && !password.isBlank()) this.password = password;
        if (telefono != null && !telefono.isBlank()) this.telefono = telefono.trim();
    }

    // Método polimórfico para identificar el rol de cada subclase
    public abstract String obtenerRol();

    public abstract void mostrarInformacion();

    // Encapsulamiento del estado de la cuenta (HU-21)
    public void activarCuenta() {
        this.activo = true;
    }

    public void desactivarCuenta() {
        this.activo = false;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public static int getTotalPersonas() { return totalPersonas; }
}