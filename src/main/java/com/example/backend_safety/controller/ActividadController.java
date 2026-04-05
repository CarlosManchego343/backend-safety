package com.example.backend_safety.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.backend_safety.model.Actividad;
import com.example.backend_safety.repository.ActividadRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/actividades")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ActividadController {

	private final ActividadRepository repo;

    @GetMapping
    public List<Actividad> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Actividad crear(@RequestBody Actividad a) {
        return repo.save(a);
    }
}
