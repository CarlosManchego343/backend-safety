package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "incidente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private java.time.LocalDate fecha;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    @JsonIgnore
    private Actividad actividad;
}
