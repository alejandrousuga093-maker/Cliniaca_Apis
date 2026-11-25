package com.clinica.clinica_api.presentation.dto;

import com.clinica.clinica_api.domain.model.Medico;

public class MedicoResponse {
    private final String nombre;
    private final String documento;
    private final int edad;
    private final String especialidad;

    public MedicoResponse(Medico medico) {
        this.nombre = medico.getNombre();
        this.documento = medico.getDocumento();
        this.edad = medico.getEdad();
        this.especialidad = medico.getEspecialidad(); // Asegúrate de que tu modelo Medico tenga estos getters
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public int getEdad() { return edad; }
    public String getEspecialidad() { return especialidad; }
}