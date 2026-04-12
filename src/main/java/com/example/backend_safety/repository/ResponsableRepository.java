package com.example.backend_safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_safety.model.Responsable;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Integer> {
}