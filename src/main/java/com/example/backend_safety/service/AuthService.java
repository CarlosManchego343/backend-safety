package com.example.backend_safety.service;

import org.springframework.stereotype.Service;

import com.example.backend_safety.config.JwtService;
import com.example.backend_safety.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UsuarioRepository repo;
    private final JwtService jwtService;

    public String login(String correo, String contrasenia) {

        var user = repo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getContrasenia().equals(contrasenia)) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtService.generateToken(user.getCorreo());
    }
}
