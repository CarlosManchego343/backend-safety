package com.example.backend_safety.service;

import com.example.backend_safety.model.Riesgo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReportePdfServiceTest {

    private final ReportePdfService reportePdfService = new ReportePdfService();

    @Test
    void deberiaGenerarPdfCorrectamente() {

        // Arrange
        Riesgo r1 = new Riesgo();
        r1.setId(1L);
        r1.setNivel("ALTO");
        r1.setEstado("ACTIVO");

        Riesgo r2 = new Riesgo();
        r2.setId(2L);
        r2.setNivel("MEDIO");
        r2.setEstado("INACTIVO");

        Map<String, Object> data = Map.of(
                "totalActividades", 5,
                "riesgosAltos", 1,
                "riesgosMedios", 1,
                "riesgosBajos", 0,
                "riesgos", List.of(r1, r2)
        );

        // Act
        byte[] pdfBytes = reportePdfService.generarPdf(data);

        // Assert
        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);
    }

    // ❌ Caso error (opcional pero bueno)
    @Test
    void deberiaLanzarErrorSiDataEsInvalida() {

        Map<String, Object> dataInvalida = Map.of(); // sin claves necesarias

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                reportePdfService.generarPdf(dataInvalida)
        );

        assertEquals("Error generando PDF", ex.getMessage());
    }
}