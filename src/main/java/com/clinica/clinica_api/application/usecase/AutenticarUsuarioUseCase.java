// AutenticarUsuarioUseCase.java
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Persona;
import com.clinica.clinica_api.domain.repository.MedicoRepository;
import com.clinica.clinica_api.domain.repository.PacienteRepository;
import com.clinica.clinica_api.domain.repository.RecepcionistaRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutenticarUsuarioUseCase {

    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final RecepcionistaRepository recepcionistaRepo;

    public AutenticarUsuarioUseCase(PacienteRepository p, MedicoRepository m, RecepcionistaRepository r) {
        this.pacienteRepo = p;
        this.medicoRepo = m;
        this.recepcionistaRepo = r;
    }

    public Persona ejecutar(String identificador, String password) {
        // Lista polimórfica de Persona
        List<Persona> todosLosUsuarios = new ArrayList<>();
        todosLosUsuarios.addAll(pacienteRepo.listarTodos());
        todosLosUsuarios.addAll(medicoRepo.listarTodos());
        todosLosUsuarios.addAll(recepcionistaRepo.listarTodos());

        Persona encontrado = todosLosUsuarios.stream()
                .filter(u -> (u.getCorreo().equalsIgnoreCase(identificador.trim())
                           || u.getDocumento().equals(identificador.trim()))
                          && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Credenciales incorrectas."));

        if (!encontrado.isActivo()) {
            throw new IllegalStateException("La cuenta está desactivada. Contacta al administrador.");
        }
        return encontrado;
    }
}