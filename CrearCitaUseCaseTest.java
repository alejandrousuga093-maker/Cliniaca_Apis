
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.domain.model.Paciente;
import com.clinica.clinica_api.domain.model.Medico;
import com.clinica.clinica_api.domain.repository.CitaRepository;
import com.clinica.clinica_api.domain.repository.PacienteRepository;
import com.clinica.clinica_api.domain.repository.MedicoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CrearCitaUseCaseTest {

    private CitaRepository citaRepository;
    private PacienteRepository pacienteRepository;
    private MedicoRepository medicoRepository;

    private CrearCitaUseCase crearCitaUseCase;

    private Paciente paciente;
    private Medico medico;

    @BeforeEach  //ejecunta un configuracion antes de cada prueva 
    void setUp() {

        citaRepository = mock(CitaRepository.class);    //mock me crea un objeto de prueva de la clase CitaRepository
        pacienteRepository = mock(PacienteRepository.class);
        medicoRepository = mock(MedicoRepository.class);

        crearCitaUseCase = new CrearCitaUseCase(
                citaRepository,
                pacienteRepository,
                medicoRepository
        );

        paciente = new Paciente(
                "Maria", "123456", 25, "Medio" //datos traidos de paciente repository
        );

        medico = new Medico(
                "Carlos", "789456", 40, "Medicina General" //datos de medico repository
        );
    }

    
    @Test
    void debeCrearCitaCorrectamente() {

        when(pacienteRepository.listarTodos()) //responde a un objeto simulado con una lista que contiene el paciente creado
                .thenReturn(List.of(paciente));

        when(medicoRepository.listarTodos())
                .thenReturn(List.of(medico));

        Cita resultado = crearCitaUseCase.ejecutar(
                "123456",
                "789456",
                "15/11/2026",
                "10:00"
        );

        assertNotNull(resultado);
        assertEquals(paciente, resultado.getPaciente());
        assertEquals(medico, resultado.getMedico());
    }

    // PRUEBA 2: Paciente inexistente
    @Test
    void debeLanzarExcepcionCuandoPacienteNoExiste() {

        when(pacienteRepository.listarTodos()) //Cuando el caso de uso consulte los pacientes, Mockito responderá con una lista que contiene nuestro paciente de prueba.
                .thenReturn(List.of());

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> crearCitaUseCase.ejecutar(
                        "999999",
                        "789456",
                        "15/11/2026",
                        "10:00"
                )
        );

        assertEquals("Paciente no encontrado", excepcion.getMessage());

        verify(citaRepository, never()).guardar(any());
    }

    // PRUEBA 3: Médico inexistente
    @Test
    void debeLanzarExcepcionCuandoMedicoNoExiste() {

        when(pacienteRepository.listarTodos())
                .thenReturn(List.of(paciente));

        when(medicoRepository.listarTodos())
                .thenReturn(List.of());

        IllegalArgumentException excepcion = assertThrows(
                IllegalArgumentException.class,
                () -> crearCitaUseCase.ejecutar(
                        "123456",
                        "999999",
                        "15/11/2026",
                        "10:00"
                )
        );

        assertEquals("Médico no encontrado", excepcion.getMessage());

        verify(citaRepository, never()).guardar(any());
    }

    // PRUEBA 4: Verificar que se guarde la cita
    @Test
    void debeGuardarLaCitaEnElRepositorio() {

        when(pacienteRepository.listarTodos())
                .thenReturn(List.of(paciente));

        when(medicoRepository.listarTodos())
                .thenReturn(List.of(medico));

        crearCitaUseCase.ejecutar(
                "123456",
                "789456",
                "15/11/2026",
                "10:00"
        );

        verify(citaRepository, times(1)).guardar(any(Cita.class)); //verifica que el metodo guardar del repositorio de citas se haya llamado exactamente una vez con cualquier objeto de tipo Cita
    }
}
//assertThrows comprueba que una ejecución lance el tipo de excepción esperado.