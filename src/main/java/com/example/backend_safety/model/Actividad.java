package com.example.backend_safety.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "actividad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String estado;

    private java.time.LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "actividad")
    private java.util.List<Riesgo> riesgos;

    @OneToMany(mappedBy = "actividad")
    private java.util.List<Incidente> incidentes;
}
