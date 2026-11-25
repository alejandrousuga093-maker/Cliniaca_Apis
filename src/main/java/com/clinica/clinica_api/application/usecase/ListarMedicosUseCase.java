package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.domain.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarMedicosUseCase {
    private final MedicoRepository medicoRepository;

    public ListarMedicosUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> ejecutar() {
        // En tu MedicoRepositoryMemoria el método se llama listarTodos
        return medicoRepository.listarTodos(); 
    }
}