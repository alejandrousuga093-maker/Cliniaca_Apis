package com.clinica.clinica_api.domain.repository;

import com.clinica.clinica_api.domain.model.Paciente;
import java.util.List;

public interface PacienteRepository {
    List<Paciente> listarTodos();

     void guardar(Paciente paciente);
}