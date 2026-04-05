package com.example.backend_safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_safety.model.Incidente;

public interface IncidenteRepository extends JpaRepository<Incidente, Long> {

}
