
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.repository.PacienteRepository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarPacientesUseCaseTest {

    @Test
    void debeListarTodosLosPacientes() {

        // Crear el repositorio simulado
        PacienteRepository repository = mock(PacienteRepository.class);

        // Crear pacientes simulados
        Paciente paciente1 = mock(Paciente.class);
        Paciente paciente2 = mock(Paciente.class);

        // Definir la respuesta del repositorio
        when(repository.listarTodos())
                .thenReturn(List.of(paciente1, paciente2));

        // Crear el caso de uso
        ListarPacientesUseCase useCase =
                new ListarPacientesUseCase(repository);

        // Ejecutar
        List<Paciente> resultado = useCase.ejecutar();

        // Comprobar resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(paciente1));
        assertTrue(resultado.contains(paciente2));

        verify(repository, times(1)).listarTodos();
    }

    @Test
    void debeDevolverListaVaciaCuandoNoHayPacientes() {

        // Crear el repositorio simulado
        PacienteRepository repository = mock(PacienteRepository.class);

        // Simular una lista vacía
        when(repository.listarTodos()).thenReturn(List.of());

        // Crear el caso de uso
        ListarPacientesUseCase useCase =
                new ListarPacientesUseCase(repository);

        // Ejecutar
        List<Paciente> resultado = useCase.ejecutar();

        // Comprobar resultados
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(repository, times(1)).listarTodos();
    }
}
