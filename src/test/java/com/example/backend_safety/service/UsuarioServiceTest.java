package com.example.backend_safety.service;

import com.example.backend_safety.model.Usuario;
import com.example.backend_safety.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService usuarioService;

    // ✅ listar()
    @Test
    void deberiaListarUsuarios() {

        List<Usuario> usuarios = List.of(new Usuario(), new Usuario());

        when(repo.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.listar();

        assertEquals(2, resultado.size());
        verify(repo).findAll();
    }

    // ✅ guardar()
    @Test
    void deberiaGuardarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setCorreo("test@correo.com");

        when(repo.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardar(usuario);

        assertNotNull(resultado);
        assertEquals("test@correo.com", resultado.getCorreo());
        verify(repo).save(usuario);
    }

    // ✅ obtener() exitoso
    @Test
    void deberiaObtenerUsuarioPorId() {

        Usuario usuario = new Usuario();
        usuario.setId(1);

        when(repo.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.obtener(1L);

        assertEquals(1, resultado.getId());
    }

    // ❌ obtener() usuario no existe
    @Test
    void deberiaLanzarErrorSiUsuarioNoExiste() {

        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                usuarioService.obtener(1L)
        );
    }
}