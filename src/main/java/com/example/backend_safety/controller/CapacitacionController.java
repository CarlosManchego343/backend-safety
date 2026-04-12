package com.example.backend_safety.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.example.backend_safety.model.Capacitacion;
import com.example.backend_safety.service.CapacitacionService;

import java.util.List;

@RestController
@RequestMapping("/api/capacitaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") 
public class CapacitacionController {

	private final CapacitacionService capacitacionService;

    @GetMapping
    public List<Capacitacion> listar() {
        return capacitacionService.listarTodas();
    }
}
