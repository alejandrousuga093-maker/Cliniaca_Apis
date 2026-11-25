
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.repository.PacienteRepository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrarPacienteUseCaseTest {

    @Test
    void debeRegistrarPacienteCorrectamente() {

        PacienteRepository repository = mock(PacienteRepository.class);

        Paciente paciente = mock(Paciente.class);

        when(paciente.getDocumento()).thenReturn("123456");
        when(repository.listarTodos()).thenReturn(List.of());

        RegistrarPacienteUseCase useCase =
                new RegistrarPacienteUseCase(repository);

        Paciente resultado = useCase.ejecutar(paciente);

        assertNotNull(resultado);
        assertSame(paciente, resultado);

        verify(repository, times(1)).guardar(paciente);
    }

    @Test
    void debeLanzarExcepcionCuandoElDocumentoYaExiste() {

        PacienteRepository repository = mock(PacienteRepository.class);

        Paciente pacienteExistente = mock(Paciente.class);
        Paciente nuevoPaciente = mock(Paciente.class);

        when(pacienteExistente.getDocumento()).thenReturn("123456");
        when(nuevoPaciente.getDocumento()).thenReturn("123456");

        when(repository.listarTodos())
                .thenReturn(List.of(pacienteExistente));

        RegistrarPacienteUseCase useCase =
                new RegistrarPacienteUseCase(repository);

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.ejecutar(nuevoPaciente)
        );

        assertEquals(
                "Ya existe un paciente con ese documento",
                excepcion.getMessage()
        );

        verify(repository, never()).guardar(any(Paciente.class));
    }

    @Test
    void debeGuardarElPacienteEnElRepositorio() {

        PacienteRepository repository = mock(PacienteRepository.class);

        Paciente paciente = mock(Paciente.class);

        when(paciente.getDocumento()).thenReturn("789456");
        when(repository.listarTodos()).thenReturn(List.of());

        RegistrarPacienteUseCase useCase =
                new RegistrarPacienteUseCase(repository);

        useCase.ejecutar(paciente);

        verify(repository, times(1)).guardar(paciente);
    }

    @Test
    void debeConsultarLosPacientesAntesDeRegistrar() {

        PacienteRepository repository = mock(PacienteRepository.class);

        Paciente paciente = mock(Paciente.class);

        when(paciente.getDocumento()).thenReturn("987654");
        when(repository.listarTodos()).thenReturn(List.of());

        RegistrarPacienteUseCase useCase =
                new RegistrarPacienteUseCase(repository);

        useCase.ejecutar(paciente);

        verify(repository, times(1)).listarTodos();
    }
}
