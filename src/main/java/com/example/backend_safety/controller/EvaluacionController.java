package com.example.backend_safety.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_safety.model.EvaluacionRiesgo;
import com.example.backend_safety.service.RiesgoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

	private final RiesgoService service;

    @PostMapping("/{riesgoId}")
    public Map<String, Object> evaluar(
            @PathVariable Long riesgoId,
            @RequestParam int probabilidad,
            @RequestParam int impacto) {

        EvaluacionRiesgo eval = service.evaluarRiesgo(riesgoId, probabilidad, impacto);

        String alerta = service.generarAlerta(eval.getRiesgo());

        Map<String, Object> response = new HashMap<>();
        response.put("evaluacion", eval);
        response.put("alerta", alerta);

        return response;
    }
}
