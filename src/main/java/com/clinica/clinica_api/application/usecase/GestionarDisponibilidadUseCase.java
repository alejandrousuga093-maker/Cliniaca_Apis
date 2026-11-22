// GestionarDisponibilidadUseCase.java
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Disponibilidad;
import com.clinica.clinica_api.domain.repository.CitaRepository;
import com.clinica.clinica_api.domain.repository.DisponibilidadRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class GestionarDisponibilidadUseCase {

    private final DisponibilidadRepository dispRepo;
    private final CitaRepository citaRepo;

    public GestionarDisponibilidadUseCase(DisponibilidadRepository dispRepo, CitaRepository citaRepo) {
        this.dispRepo = dispRepo;
        this.citaRepo = citaRepo;
    }

    public List<Disponibilidad> listarPorMedico(String documentoMedico) {
        return dispRepo.listarTodas().stream()
                .filter(d -> d.getDocumentoMedico().equals(documentoMedico))
                .toList();
    }

    public Disponibilidad registrar(Disponibilidad nueva) {
        boolean cruce = listarPorMedico(nueva.getDocumentoMedico()).stream()
                .anyMatch(d -> d.getFecha().equals(nueva.getFecha())
                        && !(nueva.getHoraFin().compareTo(d.getHoraInicio()) <= 0
                          || nueva.getHoraInicio().compareTo(d.getHoraFin()) >= 0));
        if (cruce) {
            throw new IllegalArgumentException("El horario se cruza con otra disponibilidad existente.");
        }
        dispRepo.guardar(nueva);
        return nueva;
    }

    public List<String> obtenerHorariosLibres(String documentoMedico, String fecha) {
        Set<String> posibles = new TreeSet<>();
        listarPorMedico(documentoMedico).stream()
                .filter(d -> d.getFecha().equals(fecha))
                .forEach(d -> {
                    int ini = Integer.parseInt(d.getHoraInicio().split(":")[0]);
                    int fin = Integer.parseInt(d.getHoraFin().split(":")[0]);
                    for (int h = ini; h < fin; h++) {
                        posibles.add(String.format("%02d:00", h));
                    }
                });

        List<String> ocupados = citaRepo.listarTodas().stream()
                .filter(c -> c.getMedico().getDocumento().equals(documentoMedico)
                          && c.getFecha().equals(fecha)
                          && !"Cancelada".equalsIgnoreCase(c.getEstado()))
                .map(c -> c.getHora())
                .toList();

        List<String> libres = new ArrayList<>(posibles);
        libres.removeAll(ocupados);
        return libres;
    }

    public void eliminar(String id) {
        dispRepo.eliminar(id);
    }
}