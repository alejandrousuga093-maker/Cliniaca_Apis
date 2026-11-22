// GestionarCitaUseCase.java (Cancelar, Reprogramar, Cambiar estado)
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.domain.repository.CitaRepository;
import org.springframework.stereotype.Service;

@Service
public class GestionarCitaUseCase {

    private final CitaRepository citaRepository;

    public GestionarCitaUseCase(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    private Cita buscarPorId(String id) {
        return citaRepository.listarTodas().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con ID: " + id));
    }

    public Cita cancelar(String id, String motivo) {
        Cita cita = buscarPorId(id);
        cita.cancelar(motivo);
        return cita;
    }

    public Cita reprogramar(String id, String nuevaFecha, String nuevaHora) {
        Cita cita = buscarPorId(id);
        boolean ocupado = citaRepository.listarTodas().stream()
                .anyMatch(c -> !c.getId().equals(id)
                        && c.getMedico().getDocumento().equals(cita.getMedico().getDocumento())
                        && c.getFecha().equals(nuevaFecha)
                        && c.getHora().equals(nuevaHora)
                        && !"Cancelada".equalsIgnoreCase(c.getEstado()));
        if (ocupado) {
            throw new IllegalStateException("El nuevo horario elegido ya está ocupado.");
        }
        cita.reprogramar(nuevaFecha, nuevaHora);
        return cita;
    }

    public Cita cambiarEstado(String id, String nuevoEstado) {
        Cita cita = buscarPorId(id);
        switch (nuevoEstado.toUpperCase()) {
            case "CONFIRMADA" -> cita.confirmar();
            case "ATENDIDA" -> cita.marcarAtendida();
            case "INCUMPLIDA" -> cita.marcarIncumplida();
            default -> throw new IllegalArgumentException("Estado no válido: " + nuevoEstado);
        }
        return cita;
    }
}