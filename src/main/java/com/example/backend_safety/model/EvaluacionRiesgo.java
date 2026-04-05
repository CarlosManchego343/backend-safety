package com.example.backend_safety.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluacion_riesgo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionRiesgo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer probabilidad;
    private Integer impacto;
    private String nivelCalculado;

    @ManyToOne
    @JoinColumn(name = "id_riesgo", unique = true)
    @JsonIgnore
    private Riesgo riesgo;
}
