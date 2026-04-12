package com.example.backend_safety.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.backend_safety.model.Capacitacion;
import com.example.backend_safety.repository.CapacitacionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapacitacionService {

	private final CapacitacionRepository capacitacionRepository;

    public List<Capacitacion> listarTodas() {
        return capacitacionRepository.findAll();
    }
}
