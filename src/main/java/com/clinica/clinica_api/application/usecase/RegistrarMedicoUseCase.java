// RegistrarMedicoUseCase.java
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.domain.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrarMedicoUseCase {

    private final MedicoRepository medicoRepository;

    public RegistrarMedicoUseCase(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Medico ejecutar(Medico medico) {
        boolean existe = medicoRepository.listarTodos().stream()
                .anyMatch(m -> m.getDocumento().equals(medico.getDocumento())
                            || m.getCorreo().equalsIgnoreCase(medico.getCorreo()));
        if (existe) {
            throw new IllegalArgumentException("Ya existe un médico con ese documento o correo.");
        }
        medicoRepository.guardar(medico);
        return medico;
    }
}