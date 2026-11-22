// DisponibilidadRepository.java
package com.clinica.clinica_api.domain.repository;

import com.clinica.clinica_api.domain.model.Disponibilidad;
import java.util.List;
import java.util.Optional;

public interface DisponibilidadRepository {
    List<Disponibilidad> listarTodas();
    Optional<Disponibilidad> buscarPorId(String id);
    void guardar(Disponibilidad disponibilidad);
    void eliminar(String id);
}