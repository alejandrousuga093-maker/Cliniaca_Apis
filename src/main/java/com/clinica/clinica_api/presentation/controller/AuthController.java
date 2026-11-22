package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.application.usecase.AutenticarUsuarioUseCase;
import com.clinica.clinica_api.application.usecase.RegistrarPacienteUseCase;
import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.model.Persona;
import com.clinica.clinica_api.presentation.dto.UsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AutenticarUsuarioUseCase autenticarUseCase;
    private final RegistrarPacienteUseCase registrarPacienteUseCase;

    public AuthController(AutenticarUsuarioUseCase autenticarUseCase, RegistrarPacienteUseCase registrarPacienteUseCase) {
        this.autenticarUseCase = autenticarUseCase;
        this.registrarPacienteUseCase = registrarPacienteUseCase;
    }

    @PostMapping("/login")
    public UsuarioResponse login(@RequestBody Map<String, String> body) {
        try {
            Persona usuario = autenticarUseCase.ejecutar(body.get("correo"), body.get("password"));
            return new UsuarioResponse(usuario);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse register(@RequestBody Paciente paciente) {
        try {
            Paciente creado = registrarPacienteUseCase.ejecutar(paciente);
            return new UsuarioResponse(creado);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}