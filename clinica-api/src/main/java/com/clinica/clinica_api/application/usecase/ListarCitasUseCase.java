package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.domain.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCitasUseCase {

    private final CitaRepository repository;

    public ListarCitasUseCase(CitaRepository repository) {
        this.repository = repository;
    }

    public List<Cita> ejecutar() {
        return repository.listarTodas();
    }
}