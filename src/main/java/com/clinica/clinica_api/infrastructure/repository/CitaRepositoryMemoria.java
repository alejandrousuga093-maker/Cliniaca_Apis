
package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.domain.repository.CitaRepository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CitaRepositoryMemoria implements CitaRepository {

    private final List<Cita> citas = new ArrayList<>();

    public CitaRepositoryMemoria() {
        Paciente paciente = new Paciente(
            "Maria", "123456", 25, "Medio"
        );

        Medico medico = new Medico(
            "Carlos", "789456", 40,
            "Medicina General"
        );

        citas.add(new Cita(
            paciente, medico,
            "20/09/2026", "10:00"
        ));
    }

    @Override
    public List<Cita> listarTodas() {
        return citas;
    }

    @Override
    public void guardar(Cita cita) {
        citas.add(cita);
    }
}
