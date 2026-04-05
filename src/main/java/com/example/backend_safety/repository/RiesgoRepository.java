package com.example.backend_safety.repository;

import org.springframework.data.jpa.repository.*;

import com.example.backend_safety.model.Riesgo;

import java.util.List;

public interface RiesgoRepository extends JpaRepository<Riesgo, Long> {
	
	List<Riesgo> findByNivel(String nivel);

    List<Riesgo> findByEstado(String estado);
}
