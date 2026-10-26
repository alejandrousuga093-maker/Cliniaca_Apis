package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.repository.PacienteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepositoryMemoria
        implements PacienteRepository {

    private final List<Paciente> pacientes = List.of(
        new Paciente("Maria", "123456", 25, "Medio"),
        new Paciente("Juan", "654321", 30, "Bajo")
    );

    @Override
    public List<Paciente> listarTodos() {
        return pacientes;
    }
}