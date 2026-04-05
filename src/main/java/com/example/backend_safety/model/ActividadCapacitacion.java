package com.example.backend_safety.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actividad_capacitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActividadCapacitacion {

	@EmbeddedId
    private ActividadCapacitacionId id;

    private String estado;
}
