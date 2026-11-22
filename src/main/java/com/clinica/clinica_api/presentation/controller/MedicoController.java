package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.application.usecase.GestionarDisponibilidadUseCase;
import com.clinica.clinica_api.application.usecase.ListarMedicosUseCase;
import com.clinica.clinica_api.application.usecase.RegistrarMedicoUseCase;
import com.clinica.clinica_api.domain.model.Disponibilidad;
import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.presentation.dto.MedicoResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final ListarMedicosUseCase listarMedicosUseCase;
    private final RegistrarMedicoUseCase registrarMedicoUseCase;
    private final GestionarDisponibilidadUseCase gestionarDisponibilidadUseCase;

    public MedicoController(
            ListarMedicosUseCase listarMedicosUseCase,
            RegistrarMedicoUseCase registrarMedicoUseCase,
            GestionarDisponibilidadUseCase gestionarDisponibilidadUseCase) {

        this.listarMedicosUseCase = listarMedicosUseCase;
        this.registrarMedicoUseCase = registrarMedicoUseCase;
        this.gestionarDisponibilidadUseCase = gestionarDisponibilidadUseCase;
    }

    // API: Listar médicos usando el DTO de respuesta
    @GetMapping
    public List<MedicoResponse> listarMedicos() {
        return listarMedicosUseCase.ejecutar()
                .stream()
                .map(MedicoResponse::new)
                .toList();
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoResponse registrarMedico(@RequestBody Medico medico) {
        try {
            return new MedicoResponse(registrarMedicoUseCase.ejecutar(medico));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/{documento}/horarios-disponibles")
    public List<String> horariosLibres(@PathVariable String documento, @RequestParam String fecha) {
        return gestionarDisponibilidadUseCase.obtenerHorariosLibres(documento, fecha);
    }

    @GetMapping("/{documento}/disponibilidad")
    public List<Disponibilidad> listarDisponibilidad(@PathVariable String documento) {
        return gestionarDisponibilidadUseCase.listarPorMedico(documento);
    }

    @PostMapping("/disponibilidad")
    @ResponseStatus(HttpStatus.CREATED)
    public Disponibilidad crearDisponibilidad(@RequestBody Disponibilidad disp) {
        try {
            return gestionarDisponibilidadUseCase.registrar(disp);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/disponibilidad/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarDisponibilidad(@PathVariable String id) {
        gestionarDisponibilidadUseCase.eliminar(id);
    }
}