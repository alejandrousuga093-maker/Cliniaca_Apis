package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.application.usecase.ListarMedicosUseCase;
import com.clinica.clinica_api.presentation.dto.MedicoResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final ListarMedicosUseCase listarMedicosUseCase;

    public MedicoController(ListarMedicosUseCase listarMedicosUseCase) {
        this.listarMedicosUseCase = listarMedicosUseCase;
    }

    // API: Listar médicos usando el DTO de respuesta
    @GetMapping
    public List<MedicoResponse> listarMedicos() {
        return listarMedicosUseCase.ejecutar()
                .stream()
                .map(MedicoResponse::new)
                .toList();
    }
}