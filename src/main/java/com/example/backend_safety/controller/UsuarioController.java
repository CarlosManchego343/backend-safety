package com.example.backend_safety.controller;

import org.springframework.web.bind.annotation.*;

import com.example.backend_safety.model.Usuario;
import com.example.backend_safety.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	private final UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario u) {
        return service.guardar(u);
    }
}
