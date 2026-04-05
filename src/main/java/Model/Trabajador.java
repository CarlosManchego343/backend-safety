package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trabajador")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Trabajador extends Usuario {
	 
	private String area;
}
