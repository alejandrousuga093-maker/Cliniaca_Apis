
package com.clinica.clinica_api.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.clinica.clinica_api.application.usecase.ListarPacientesUseCase;
import com.clinica.clinica_api.application.usecase.RegistrarPacienteUseCase;
import com.clinica.clinica_api.domain.model.Paciente;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final ListarPacientesUseCase listarPacientesUseCase;
    private final RegistrarPacienteUseCase registrarPacienteUseCase;

    public PacienteController(
            ListarPacientesUseCase listarPacientesUseCase,
            RegistrarPacienteUseCase registrarPacienteUseCase) {

        this.listarPacientesUseCase = listarPacientesUseCase;
        this.registrarPacienteUseCase = registrarPacienteUseCase;
    }

    // API para listar pacientes
    @GetMapping
    public Object listarPacientes() {
        return listarPacientesUseCase.ejecutar();
    }

    // API para registrar un paciente
    @PostMapping("/registrar")
    public Paciente registrarPaciente(
            @RequestBody Paciente paciente) {

        try {
            return registrarPacienteUseCase.ejecutar(paciente);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    e.getMessage()
            );
        }
    }
}
