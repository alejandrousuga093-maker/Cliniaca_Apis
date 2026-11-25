
package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.domain.repository.CitaRepository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository         //permite que la clase sea detectada por Spring y se pueda inyectar en otras clases.
public class CitaRepositoryMemoria implements CitaRepository {

    private final List<Cita> citas = new ArrayList<>(); //es la implementacion utilizada para implementar los datos de la cita en memoria, es decir, no se utiliza una base de datos real, sino que se almacenan en una lista en memoria.

    public CitaRepositoryMemoria() {      //constructor de la clase, se ejecuta cuando se crea una instancia de la clase.
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
    public List<Cita> listarTodas() {   //retorna la lista de citas almacenadas en memoria.
        return citas;
    }

    @Override
    public void guardar(Cita cita) { //agrega una nueva cita a la lista de citas almacenadas en memoria.
        citas.add(cita);
    }
}
