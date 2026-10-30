package com.clinica.clinica_api.domain.repository;

import com.clinica.clinica_api.domain.model.Medico;
import java.util.List;

public interface MedicoRepository {
    List<Medico> listarTodos();

     void guardar(Medico medico);
}