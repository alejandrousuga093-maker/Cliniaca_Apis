// EspecialidadRepositoryMemoria.java
package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Especialidad;
import com.clinica.clinica_api.domain.repository.EspecialidadRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EspecialidadRepositoryMemoria implements EspecialidadRepository {
    private final List<Especialidad> especialidades = new ArrayList<>();

    public EspecialidadRepositoryMemoria() {
        especialidades.add(new Especialidad("Medicina General", "Atención primaria integral y chequeos preventivos."));
        especialidades.add(new Especialidad("Cardiología", "Evaluación y tratamiento cardiovascular."));
        especialidades.add(new Especialidad("Pediatría", "Cuidado médico especializado para niños."));
        especialidades.add(new Especialidad("Dermatología", "Diagnóstico y tratamiento clínico de la piel."));
    }

    @Override
    public List<Especialidad> listarTodas() { return especialidades; }

    @Override
    public Optional<Especialidad> buscarPorId(String id) {
        return especialidades.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public void guardar(Especialidad e) { especialidades.add(e); }
}