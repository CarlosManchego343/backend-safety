package com.example.backend_safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_safety.model.Actividad;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
	
	@Query("""
	        SELECT a FROM Actividad a
	        WHERE (:fechaInicio IS NULL OR a.fecha >= :fechaInicio)
	        AND (:fechaFin IS NULL OR a.fecha <= :fechaFin)
	        AND (:usuarioId IS NULL OR a.usuario.id = :usuarioId)
	    """)
	    List<Actividad> filtrarActividades(
	            LocalDate fechaInicio,
	            LocalDate fechaFin,
	            Long usuarioId
	    );
}
