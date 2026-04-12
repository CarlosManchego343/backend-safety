package com.example.backend_safety.service;

import org.springframework.stereotype.Service;

import com.example.backend_safety.config.JwtService;
import com.example.backend_safety.repository.UsuarioRepository;
import com.example.backend_safety.repository.ResponsableRepository;
import com.example.backend_safety.repository.TrabajadorRepository;
import com.example.backend_safety.dto.LoginResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repo;
    private final ResponsableRepository responsableRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final JwtService jwtService;

    public LoginResponse login(String correo, String contrasenia) {

        // 🔍 Buscar usuario
        var user = repo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 🔐 Validar contraseña
        if (!user.getContrasenia().equals(contrasenia)) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 🎯 Determinar rol (según tus tablas)
        boolean esResponsable = responsableRepository.existsById(user.getId());
        boolean esTrabajador = trabajadorRepository.existsById(user.getId());
        
        System.out.println("ID: " + user.getId());
        System.out.println("Correo: " + correo);
        System.out.println("Responsable: " + responsableRepository.existsById(user.getId()));
        System.out.println("Trabajador: " + trabajadorRepository.existsById(user.getId()));

        String rol;

        if (esResponsable) {
            rol = "RESPONSABLE";
        } else if (esTrabajador) {
            rol = "TRABAJADOR";
        } else {
            throw new RuntimeException("Usuario sin rol asignado");
        }

        // 🔑 Generar token (SIN modificar tu JWT actual)
        String token = jwtService.generateToken(user.getCorreo());

        // 📦 Retornar ambos
        return new LoginResponse(token, rol);
    }
}