package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.domain.repository.MedicoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicoRepositoryMemoria implements MedicoRepository {

    // Se usa ArrayList para que la lista sea mutable y permita agregar elementos
    private final List<Medico> medicos = new ArrayList<>(List.of(
        new Medico("Maria", "123456", 25, "Neurologa"),
        new Medico("Juan", "654321", 30, "Cirujano Cardiotoracico")
    ));

    @Override
    public List<Medico> listarTodos() {
        return medicos;
    }

   
    @Override
    public void guardar(Medico medico) {
        medicos.add(medico);
    }
}