package com.clinica.clinica_api.application.usecase;

import org.springframework.stereotype.Service;

import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.repository.PacienteRepository;

@Service
public class RegistrarPacienteUseCase {

    private final PacienteRepository repository;

    public RegistrarPacienteUseCase(
            PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente ejecutar(Paciente paciente) {

        boolean existe = repository.listarTodos()
                .stream()
                .anyMatch(p -> p.getDocumento()
                        .equals(paciente.getDocumento()));

        if (existe) {
            throw new IllegalArgumentException(
                    "Ya existe un paciente con ese documento"
            );
        }

        repository.guardar(paciente);

        return paciente;
    }
}