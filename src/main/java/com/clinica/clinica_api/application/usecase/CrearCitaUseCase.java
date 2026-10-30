
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.domain.repository.CitaRepository;
import com.clinica.clinica_api.domain.repository.PacienteRepository;
import com.clinica.clinica_api.domain.repository.MedicoRepository;

import org.springframework.stereotype.Service;

@Service
public class CrearCitaUseCase {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public CrearCitaUseCase(
            CitaRepository citaRepository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository) {

        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Cita ejecutar(
            String documentoPaciente,
            String documentoMedico,
            String fecha,
            String hora) {

        // Buscar al paciente por su documento.
        Paciente paciente = pacienteRepository.listarTodos()
                .stream()
                .filter(p -> p.getDocumento()
                        .equals(documentoPaciente))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Paciente no encontrado"));

        // Buscar al médico por su documento.
        Medico medico = medicoRepository.listarTodos()
                .stream()
                .filter(m -> m.getDocumento()
                        .equals(documentoMedico))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Médico no encontrado"));

        // Crear la cita con las entidades existentes.
        Cita cita = new Cita(
                paciente,
                medico,
                fecha,
                hora
        );

        // Guardar la cita en el repositorio.
        citaRepository.guardar(cita);

        return cita;
    }
}
