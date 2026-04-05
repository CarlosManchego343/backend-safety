package Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActividadCapacitacionId implements java.io.Serializable {

	private Long idActividad;
    private Long idCapacitacion;
}
