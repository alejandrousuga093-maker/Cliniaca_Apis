// RecepcionistaRepositoryMemoria.java
package com.clinica.clinica_api.infrastructure.repository;

import com.clinica.clinica_api.domain.model.Recepcionista;
import com.clinica.clinica_api.domain.repository.RecepcionistaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecepcionistaRepositoryMemoria implements RecepcionistaRepository {
    private final List<Recepcionista> recepcionistas = new ArrayList<>();

    public RecepcionistaRepositoryMemoria() {
        recepcionistas.add(new Recepcionista("Laura Ospina", "1020456789", 29, "Mañana", "admin@medik.com", "123", "3052219000"));
    }

    @Override
    public List<Recepcionista> listarTodos() { return recepcionistas; }

    @Override
    public void guardar(Recepcionista r) { recepcionistas.add(r); }
}