package com.example.backend_safety.service;

import com.example.backend_safety.model.*;
import com.example.backend_safety.repository.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RiesgoServiceTest {

    @Mock
    private RiesgoRepository riesgoRepo;

    @Mock
    private EvaluacionRiesgoRepository evalRepo;

    @InjectMocks
    private RiesgoService riesgoService;

    // ❌ Riesgo no encontrado
    @Test
    void deberiaLanzarErrorSiRiesgoNoExiste() {
        when(riesgoRepo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                riesgoService.evaluarRiesgo(1L, 2, 2)
        );

        assertEquals("Riesgo no encontrado", ex.getMessage());
    }

    // ✅ Crear evaluación nueva (BAJO)
    @Test
    void deberiaCrearEvaluacionBajo() {

        Riesgo riesgo = new Riesgo();
        riesgo.setId(1L);

        when(riesgoRepo.findById(1L)).thenReturn(Optional.of(riesgo));
        when(evalRepo.findByRiesgo(riesgo)).thenReturn(Optional.empty());

        EvaluacionRiesgo resultado = riesgoService.evaluarRiesgo(1L, 1, 2); // valor = 2

        assertEquals("BAJO", resultado.getNivelCalculado());
        verify(evalRepo).save(any());
        verify(riesgoRepo).save(riesgo);
    }

    // ✅ Nivel MEDIO
    @Test
    void deberiaCalcularNivelMedio() {

        Riesgo riesgo = new Riesgo();
        riesgo.setId(1L);

        when(riesgoRepo.findById(1L)).thenReturn(Optional.of(riesgo));
        when(evalRepo.findByRiesgo(riesgo)).thenReturn(Optional.empty());

        EvaluacionRiesgo resultado = riesgoService.evaluarRiesgo(1L, 3, 4); // 12

        assertEquals("MEDIO", resultado.getNivelCalculado());
    }

    // ✅ Nivel ALTO
    @Test
    void deberiaCalcularNivelAlto() {

        Riesgo riesgo = new Riesgo();
        riesgo.setId(1L);

        when(riesgoRepo.findById(1L)).thenReturn(Optional.of(riesgo));
        when(evalRepo.findByRiesgo(riesgo)).thenReturn(Optional.empty());

        EvaluacionRiesgo resultado = riesgoService.evaluarRiesgo(1L, 5, 4); // 20

        assertEquals("ALTO", resultado.getNivelCalculado());
    }

    // ✅ Actualizar evaluación existente
    @Test
    void deberiaActualizarEvaluacionExistente() {

        Riesgo riesgo = new Riesgo();
        riesgo.setId(1L);

        EvaluacionRiesgo existente = new EvaluacionRiesgo();
        existente.setNivelCalculado("BAJO");

        when(riesgoRepo.findById(1L)).thenReturn(Optional.of(riesgo));
        when(evalRepo.findByRiesgo(riesgo)).thenReturn(Optional.of(existente));

        EvaluacionRiesgo resultado = riesgoService.evaluarRiesgo(1L, 5, 4);

        assertEquals("ALTO", resultado.getNivelCalculado());
    }

    // ✅ puedeEjecutarse - false si hay ALTO
    @Test
    void noDebeEjecutarseSiHayRiesgoAlto() {

        Riesgo r = new Riesgo();
        r.setNivel("ALTO");

        Actividad actividad = new Actividad();
        actividad.setRiesgos(List.of(r));

        boolean resultado = riesgoService.puedeEjecutarse(actividad);

        assertFalse(resultado);
    }

    // ✅ puedeEjecutarse - true si no hay ALTO
    @Test
    void debeEjecutarseSiNoHayRiesgoAlto() {

        Riesgo r = new Riesgo();
        r.setNivel("BAJO");

        Actividad actividad = new Actividad();
        actividad.setRiesgos(List.of(r));

        boolean resultado = riesgoService.puedeEjecutarse(actividad);

        assertTrue(resultado);
    }

    // ✅ generarAlerta ALTO
    @Test
    void deberiaGenerarAlertaAlto() {

        Riesgo r = new Riesgo();
        r.setNivel("ALTO");

        String mensaje = riesgoService.generarAlerta(r);

        assertTrue(mensaje.contains("ALERTA"));
    }

    // ✅ generarAlerta MEDIO
    @Test
    void deberiaGenerarAlertaMedio() {

        Riesgo r = new Riesgo();
        r.setNivel("MEDIO");

        String mensaje = riesgoService.generarAlerta(r);

        assertTrue(mensaje.contains("Precaución"));
    }

    // ✅ generarAlerta BAJO
    @Test
    void deberiaGenerarAlertaBajo() {

        Riesgo r = new Riesgo();
        r.setNivel("BAJO");

        String mensaje = riesgoService.generarAlerta(r);

        assertTrue(mensaje.contains("Riesgo bajo"));
    }
}