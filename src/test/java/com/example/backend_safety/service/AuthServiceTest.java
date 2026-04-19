package com.example.backend_safety.service;

import com.example.backend_safety.config.JwtService;
import com.example.backend_safety.dto.LoginResponse;
import com.example.backend_safety.model.Usuario;
import com.example.backend_safety.repository.ResponsableRepository;
import com.example.backend_safety.repository.TrabajadorRepository;
import com.example.backend_safety.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository repo;

    @Mock
    private ResponsableRepository responsableRepository;

    @Mock
    private TrabajadorRepository trabajadorRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setCorreo("test@correo.com");
        usuario.setContrasenia("1234");
    }

    // ✅ Login como RESPONSABLE
    @Test
    void deberiaLoginComoResponsable() {
        when(repo.findByCorreo("test@correo.com")).thenReturn(Optional.of(usuario));
        when(responsableRepository.existsById(1)).thenReturn(true);
        when(trabajadorRepository.existsById(1)).thenReturn(false);
        when(jwtService.generateToken("test@correo.com")).thenReturn("token123");

        LoginResponse response = authService.login("test@correo.com", "1234");

        assertEquals("token123", response.getToken());
        assertEquals("RESPONSABLE", response.getRol());
    }

    // ✅ Login como TRABAJADOR
    @Test
    void deberiaLoginComoTrabajador() {
        when(repo.findByCorreo("test@correo.com")).thenReturn(Optional.of(usuario));
        when(responsableRepository.existsById(1)).thenReturn(false);
        when(trabajadorRepository.existsById(1)).thenReturn(true);
        when(jwtService.generateToken("test@correo.com")).thenReturn("token123");

        LoginResponse response = authService.login("test@correo.com", "1234");

        assertEquals("TRABAJADOR", response.getRol());
    }

    // ❌ Usuario no encontrado
    @Test
    void deberiaLanzarErrorSiUsuarioNoExiste() {
        when(repo.findByCorreo("test@correo.com")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                authService.login("test@correo.com", "1234")
        );

        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    // ❌ Contraseña incorrecta
    @Test
    void deberiaLanzarErrorSiContraseniaIncorrecta() {
        when(repo.findByCorreo("test@correo.com")).thenReturn(Optional.of(usuario));

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                authService.login("test@correo.com", "wrong")
        );

        assertEquals("Credenciales inválidas", ex.getMessage());
    }

    // ❌ Usuario sin rol
    @Test
    void deberiaLanzarErrorSiUsuarioSinRol() {
        when(repo.findByCorreo("test@correo.com")).thenReturn(Optional.of(usuario));
        when(responsableRepository.existsById(1)).thenReturn(false);
        when(trabajadorRepository.existsById(1)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                authService.login("test@correo.com", "1234")
        );

        assertEquals("Usuario sin rol asignado", ex.getMessage());
    }
}