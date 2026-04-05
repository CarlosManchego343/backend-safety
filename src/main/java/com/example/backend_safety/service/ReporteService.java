package com.example.backend_safety.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.backend_safety.model.*;
import com.example.backend_safety.repository.*;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReporteService {

	private final ActividadRepository actividadRepo;
    private final RiesgoRepository riesgoRepo;

    public Map<String, Object> generarReporte(
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Long usuarioId,
            String nivelRiesgo,
            String estadoRiesgo
    ) {

        List<Actividad> actividades = actividadRepo.filtrarActividades(
                fechaInicio, fechaFin, usuarioId
        );

        List<Riesgo> riesgos = new ArrayList<>();

        if (nivelRiesgo != null) {
            riesgos = riesgoRepo.findByNivel(nivelRiesgo);
        }

        if (estadoRiesgo != null) {
            riesgos = riesgoRepo.findByEstado(estadoRiesgo);
        }

        long totalActividades = actividades.size();

        long riesgosAltos = riesgos.stream()
                .filter(r -> "ALTO".equals(r.getNivel()))
                .count();

        long riesgosMedios = riesgos.stream()
                .filter(r -> "MEDIO".equals(r.getNivel()))
                .count();

        long riesgosBajos = riesgos.stream()
                .filter(r -> "BAJO".equals(r.getNivel()))
                .count();

        Map<String, Object> reporte = new HashMap<>();

        reporte.put("totalActividades", totalActividades);
        reporte.put("riesgosAltos", riesgosAltos);
        reporte.put("riesgosMedios", riesgosMedios);
        reporte.put("riesgosBajos", riesgosBajos);
        reporte.put("actividades", actividades);
        reporte.put("riesgos", riesgos);

        return reporte;
    }
}
