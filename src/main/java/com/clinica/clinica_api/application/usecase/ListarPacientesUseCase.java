package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPacientesUseCase {

    private final PacienteRepository repository;

    public ListarPacientesUseCase(
            PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> ejecutar() {
        return repository.listarTodos();
    }
}
