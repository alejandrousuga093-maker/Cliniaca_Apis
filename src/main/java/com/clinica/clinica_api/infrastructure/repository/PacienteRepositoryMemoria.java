package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.repository.PacienteRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteRepositoryMemoria implements PacienteRepository {

    private final List<Paciente> pacientes = new ArrayList<>();

    public PacienteRepositoryMemoria() {
        pacientes.add(new Paciente("Maria Bonilla", "123456", 25, "Medio"));
        pacientes.add(new Paciente("Juan Perez", "987654", 34, "Alto"));
        pacientes.add(new Paciente("Pedro Gomez", "111222", 45, "Alto"));
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacientes;
    }

    @Override
    public void guardar(Paciente paciente) {
        pacientes.add(paciente);
    }
}
