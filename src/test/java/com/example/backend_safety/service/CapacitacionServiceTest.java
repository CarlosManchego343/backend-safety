package com.example.backend_safety.service;

import com.example.backend_safety.model.Capacitacion;
import com.example.backend_safety.repository.CapacitacionRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapacitacionServiceTest {

    @Mock
    private CapacitacionRepository capacitacionRepository;

    @InjectMocks
    private CapacitacionService capacitacionService;

    @Test
    void deberiaListarTodasLasCapacitaciones() {

        // Arrange
        List<Capacitacion> listaMock = List.of(
                new Capacitacion(),
                new Capacitacion()
        );

        when(capacitacionRepository.findAll()).thenReturn(listaMock);

        // Act
        List<Capacitacion> resultado = capacitacionService.listarTodas();

        // Assert
        assertEquals(2, resultado.size());
        verify(capacitacionRepository).findAll();
    }
}