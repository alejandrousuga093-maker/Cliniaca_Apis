
package com.clinica.clinica_api.application.usecase;

import com.clinica.clinica_api.domain.model.Cita;
import com.clinica.clinica_api.domain.repository.CitaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListarCitasUseCaseTest {

    private CitaRepository citaRepository;
    private ListarCitasUseCase listarCitasUseCase;

    @BeforeEach
    void setUp() {
        citaRepository = mock(CitaRepository.class);
        listarCitasUseCase = new ListarCitasUseCase(citaRepository);
    }

    // PRUEBA 5: Listar citas registradas
    @Test
    void debeListarTodasLasCitas() {

        Cita cita1 = mock(Cita.class);
        Cita cita2 = mock(Cita.class);

        when(citaRepository.listarTodas())
                .thenReturn(List.of(cita1, cita2));

        List<Cita> resultado = listarCitasUseCase.ejecutar();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(cita1));
        assertTrue(resultado.contains(cita2));

        verify(citaRepository, times(1)).listarTodas();
    }

    // PRUEBA 6: Listar cuando no existen citas
    @Test
    void debeDevolverListaVaciaCuandoNoHayCitas() {

        when(citaRepository.listarTodas())
                .thenReturn(List.of());

        List<Cita> resultado = listarCitasUseCase.ejecutar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(citaRepository, times(1)).listarTodas();
    }
}
