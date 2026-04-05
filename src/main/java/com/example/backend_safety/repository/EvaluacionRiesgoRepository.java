package com.example.backend_safety.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_safety.model.EvaluacionRiesgo;
import com.example.backend_safety.model.Riesgo;

public interface EvaluacionRiesgoRepository extends JpaRepository<EvaluacionRiesgo, Long> {
	Optional<EvaluacionRiesgo> findByRiesgo(Riesgo riesgo);
}
