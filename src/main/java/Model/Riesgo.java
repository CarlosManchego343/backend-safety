package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "riesgo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Riesgo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String nivel;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    @JsonIgnore
    private Actividad actividad;

    @OneToMany(mappedBy = "riesgo")
    private java.util.List<EvaluacionRiesgo> evaluaciones;

    @OneToMany(mappedBy = "riesgo")
    private java.util.List<MedidaControl> medidas;
}
