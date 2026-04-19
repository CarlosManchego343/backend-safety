package com.example.backend_safety.service;

import com.example.backend_safety.model.Actividad;
import com.example.backend_safety.model.Riesgo;
import com.example.backend_safety.repository.ActividadRepository;
import com.example.backend_safety.repository.RiesgoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {

    @Mock
    private ActividadRepository actividadRepo;

    @Mock
    private RiesgoRepository riesgoRepo;

    @InjectMocks
    private ReporteService reporteService;

    // ✅ Caso general
    @Test
    void deberiaGenerarReporteCorrectamente() {

        // Arrange
        List<Actividad> actividades = List.of(new Actividad(), new Actividad());

        Riesgo r1 = new Riesgo(); r1.setNivel("ALTO");
        Riesgo r2 = new Riesgo(); r2.setNivel("MEDIO");
        Riesgo r3 = new Riesgo(); r3.setNivel("BAJO");

        List<Riesgo> riesgos = List.of(r1, r2, r3);

        when(actividadRepo.filtrarActividades(any(), any(), any()))
                .thenReturn(actividades);

        when(riesgoRepo.findByNivel("ALTO"))
                .thenReturn(riesgos);

        // Act
        Map<String, Object> resultado = reporteService.generarReporte(
                LocalDate.now(),
                LocalDate.now(),
                1L,
                "ALTO",
                null
        );

        // Assert
        assertEquals(2L, resultado.get("totalActividades"));
        assertEquals(1L, resultado.get("riesgosAltos"));
        assertEquals(1L, resultado.get("riesgosMedios"));
        assertEquals(1L, resultado.get("riesgosBajos"));
    }

    // ✅ Filtro por estado
    @Test
    void deberiaFiltrarPorEstado() {

        List<Actividad> actividades = List.of();

        Riesgo r1 = new Riesgo(); r1.setNivel("ALTO");
        Riesgo r2 = new Riesgo(); r2.setNivel("ALTO");

        when(actividadRepo.filtrarActividades(any(), any(), any()))
                .thenReturn(actividades);

        when(riesgoRepo.findByEstado("ACTIVO"))
                .thenReturn(List.of(r1, r2));

        Map<String, Object> resultado = reporteService.generarReporte(
                LocalDate.now(),
                LocalDate.now(),
                1L,
                null,
                "ACTIVO"
        );

        assertEquals(2L, resultado.get("riesgosAltos"));
    }

    // ⚠️ Caso importante: ambos filtros (tu lógica actual sobrescribe)
    @Test
    void deberiaSobrescribirFiltroSiAmbosExisten() {

        when(actividadRepo.filtrarActividades(any(), any(), any()))
                .thenReturn(List.of());

        when(riesgoRepo.findByNivel("ALTO"))
                .thenReturn(List.of(new Riesgo()));

        when(riesgoRepo.findByEstado("ACTIVO"))
                .thenReturn(List.of()); // este debe prevalecer

        Map<String, Object> resultado = reporteService.generarReporte(
                LocalDate.now(),
                LocalDate.now(),
                1L,
                "ALTO",
                "ACTIVO"
        );

        assertEquals(0L, resultado.get("riesgosAltos"));
    }

    // ✅ Sin filtros
    @Test
    void deberiaGenerarReporteSinFiltros() {

        when(actividadRepo.filtrarActividades(any(), any(), any()))
                .thenReturn(List.of(new Actividad()));

        Map<String, Object> resultado = reporteService.generarReporte(
                LocalDate.now(),
                LocalDate.now(),
                1L,
                null,
                null
        );

        assertEquals(1L, resultado.get("totalActividades"));
        assertEquals(0L, resultado.get("riesgosAltos"));
    }
}