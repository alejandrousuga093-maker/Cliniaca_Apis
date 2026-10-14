package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.application.usecase.ListarPacientesUseCase;
import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.presentation.dto.PacienteResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final ListarPacientesUseCase useCase;

    public PacienteController(
            ListarPacientesUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<PacienteResponse> listarPacientes() {
        return useCase.ejecutar()
                .stream()
                .map(PacienteResponse::new)
                .toList();
    }
}