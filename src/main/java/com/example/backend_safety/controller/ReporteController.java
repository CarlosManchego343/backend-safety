package com.example.backend_safety.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.backend_safety.service.ReportePdfService;
import com.example.backend_safety.service.ReporteService;

import org.springframework.http.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ReporteController {

	private final ReporteService reporteService;
    private final ReportePdfService pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarPdf(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) String nivelRiesgo,
            @RequestParam(required = false) String estadoRiesgo
    ) {

        var data = reporteService.generarReporte(
                fechaInicio != null ? LocalDate.parse(fechaInicio) : null,
                fechaFin != null ? LocalDate.parse(fechaFin) : null,
                usuarioId,
                nivelRiesgo,
                estadoRiesgo
        );

        byte[] pdf = pdfService.generarPdf(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
