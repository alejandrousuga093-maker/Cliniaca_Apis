// EspecialidadRepository.java
package com.clinica.clinica_api.domain.repository;

import com.clinica.clinica_api.domain.model.Especialidad;
import java.util.List;
import java.util.Optional;

public interface EspecialidadRepository {
    List<Especialidad> listarTodas();
    Optional<Especialidad> buscarPorId(String id);
    void guardar(Especialidad especialidad);
}