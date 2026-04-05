package com.example.backend_safety.model;

import jakarta.persistence.*;

import lombok.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String tipo;
	    private LocalDate fechaGeneracion;

	    @ManyToOne
	    @JoinColumn(name = "id_usuario")
	    @JsonIgnore
	    private Usuario usuario;
}
