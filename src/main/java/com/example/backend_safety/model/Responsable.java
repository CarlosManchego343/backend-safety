package com.example.backend_safety.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsable")
@Data
@NoArgsConstructor
public class Responsable {

    @Id
    private Integer id;

    private String cargo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Usuario usuario;
}