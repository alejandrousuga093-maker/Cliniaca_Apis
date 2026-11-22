// DisponibilidadRepositoryMemoria.java
package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Disponibilidad;
import com.clinica.clinica_api.domain.repository.DisponibilidadRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DisponibilidadRepositoryMemoria implements DisponibilidadRepository {
    private final List<Disponibilidad> disponibilidades = new ArrayList<>();

    public DisponibilidadRepositoryMemoria() {
        String hoy = LocalDate.now().toString();
        String manana = LocalDate.now().plusDays(1).toString();
        // Disponibilidades iniciales vinculadas a documentos de médicos
        disponibilidades.add(new Disponibilidad("71389452", hoy, "08:00", "13:00"));
        disponibilidades.add(new Disponibilidad("71389452", manana, "08:00", "12:00"));
    }

    @Override
    public List<Disponibilidad> listarTodas() { return disponibilidades; }

    @Override
    public Optional<Disponibilidad> buscarPorId(String id) {
        return disponibilidades.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    @Override
    public void guardar(Disponibilidad d) { disponibilidades.add(d); }

    @Override
    public void eliminar(String id) { disponibilidades.removeIf(d -> d.getId().equals(id)); }
}