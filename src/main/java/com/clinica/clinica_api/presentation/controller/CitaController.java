package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.application.usecase.CrearCitaUseCase;
import com.clinica.clinica_api.application.usecase.GestionarCitaUseCase;
import com.clinica.clinica_api.application.usecase.ListarCitasUseCase;
import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.presentation.dto.CitaResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final ListarCitasUseCase listarCitasUseCase;
    private final CrearCitaUseCase crearCitaUseCase;
    private final GestionarCitaUseCase gestionarCitaUseCase;

    public CitaController(
            ListarCitasUseCase listarCitasUseCase,
            CrearCitaUseCase crearCitaUseCase,
            GestionarCitaUseCase gestionarCitaUseCase) {

        this.listarCitasUseCase = listarCitasUseCase;
        this.crearCitaUseCase = crearCitaUseCase;
        this.gestionarCitaUseCase = gestionarCitaUseCase;
    }

    // API 1: Listar citas
    @GetMapping
    public List<CitaResponse> listarCitas() {
        return listarCitasUseCase.ejecutar()
                .stream()
                .map(CitaResponse::new)
                .toList();
    }

    // API 2: Registrar una cita
    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public CitaResponse registrarCita(@RequestBody NuevaCita solicitud) {
        try {
            Cita cita = crearCitaUseCase.ejecutar(
                    solicitud.getDocumentoPaciente(),
                    solicitud.getDocumentoMedico(),
                    solicitud.getFecha(),
                    solicitud.getHora()
            );
            return new CitaResponse(cita);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        }
    }

    // API 3: Cancelar cita (HU-03)
    @PutMapping("/{id}/cancelar")
    public CitaResponse cancelarCita(@PathVariable String id, @RequestBody(required = false) Map<String, String> body) {
        try {
            String motivo = (body != null) ? body.get("motivo") : "Cancelada por el usuario";
            return new CitaResponse(gestionarCitaUseCase.cancelar(id, motivo));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // API 4: Reprogramar cita (HU-04)
    @PutMapping("/{id}/reprogramar")
    public CitaResponse reprogramarCita(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            return new CitaResponse(gestionarCitaUseCase.reprogramar(id, body.get("fecha"), body.get("hora")));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // API 5: Confirmar / Atender / Marcar incumplida (HU-13)
    @PutMapping("/{id}/estado")
    public CitaResponse actualizarEstado(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            return new CitaResponse(gestionarCitaUseCase.cambiarEstado(id, body.get("estado")));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public static class NuevaCita {

        private String documentoPaciente;
        private String documentoMedico;
        private String fecha;
        private String hora;

        public NuevaCita() {
        }

        public String getDocumentoPaciente() {
            return documentoPaciente;
        }

        public void setDocumentoPaciente(String documentoPaciente) {
            this.documentoPaciente = documentoPaciente;
        }

        public String getDocumentoMedico() {
            return documentoMedico;
        }

        public void setDocumentoMedico(String documentoMedico) {
            this.documentoMedico = documentoMedico;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }
    }
}