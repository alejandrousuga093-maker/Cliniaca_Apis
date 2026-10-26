package com.clinica.clinica_api.domain.repository;

import com.clinica.clinica_api.domain.model.Cita;
import java.util.List;

public interface CitaRepository {
    List<Cita> listarTodas(); //aca indica que devolvera una lista de objetos
}