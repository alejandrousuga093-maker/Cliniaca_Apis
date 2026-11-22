// RecepcionistaRepository.java
package com.clinica.clinica_api.domain.repository;

import com.clinica.clinica_api.domain.model.Recepcionista;
import java.util.List;

public interface RecepcionistaRepository {
    List<Recepcionista> listarTodos();
    void guardar(Recepcionista recepcionista);
}