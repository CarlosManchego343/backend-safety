package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsable")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Responsable extends Usuario {
	
	private String cargo;
}
