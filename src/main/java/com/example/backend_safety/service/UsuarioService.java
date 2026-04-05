package com.example.backend_safety.service;

import org.springframework.stereotype.Service;

import com.example.backend_safety.model.Usuario;
import com.example.backend_safety.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repo;

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario guardar(Usuario u) {
        return repo.save(u);
    }

    public Usuario obtener(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
