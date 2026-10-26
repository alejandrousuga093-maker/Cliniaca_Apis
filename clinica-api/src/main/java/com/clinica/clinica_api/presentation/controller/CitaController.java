package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.application.usecase.ListarCitasUseCase;
import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.presentation.dto.CitaResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final ListarCitasUseCase useCase;

    public CitaController(
            ListarCitasUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<CitaResponse> listarCitas() {
        return useCase.ejecutar()
                .stream()
                .map(CitaResponse::new)
                .toList();
    }
}