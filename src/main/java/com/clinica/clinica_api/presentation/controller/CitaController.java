package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.application.usecase.ListarCitasUseCase;
import com.clinica.clinica_api.application.usecase.CrearCitaUseCase;
import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.presentation.dto.CitaResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*") //perimitir solicitudes desde cualquier origen

@RestController                                   //indica que esta clase es un controlador REST
@RequestMapping("/api/citas")                     //indica la ruta base para las solicitudes HTTP
public class CitaController {

    private final ListarCitasUseCase listarCitasUseCase; //inyección de dependencias para el caso de uso de listar citas
    private final CrearCitaUseCase crearCitaUseCase;     //inyección de dependencias para el caso de uso de crear citas

    public CitaController(                              //constructor para inyectar las dependencias
            ListarCitasUseCase listarCitasUseCase,      //inyección de dependencias para el caso de uso de listar citas
            CrearCitaUseCase crearCitaUseCase) {

        this.listarCitasUseCase = listarCitasUseCase;    //asignación de la dependencia para el caso de uso de listar citas
        this.crearCitaUseCase = crearCitaUseCase;        //asignación de la dependencia para el caso de uso de crear citas
    }

    // API 1: Listar citas
    @GetMapping                                               //indica que este método maneja solicitudes HTTP GET
    public List<CitaResponse> listarCitas() {                 //método para listar citas
        return listarCitasUseCase.ejecutar()                  //llama al caso de uso para listar citas
                .stream()
                .map(CitaResponse::new)
                .toList();
    }

    // API 2: Registrar una cita
    @PostMapping("/registrar")                                //indica que este método maneja solicitudes HTTP POST en la ruta /registrar
    @ResponseStatus(HttpStatus.CREATED)                       //indica que la respuesta HTTP tendrá el estado 201 Created
    public CitaResponse registrarCita(                        //método para registrar una nueva cita
            @RequestBody NuevaCita solicitud) {               //indica que el cuerpo de la solicitud HTTP se deserializará en un objeto NuevaCita

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

    public static class NuevaCita { //clase interna para representar la solicitud de creación de una nueva cita

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