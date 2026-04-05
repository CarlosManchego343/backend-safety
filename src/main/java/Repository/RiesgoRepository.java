package Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;
import Model.Riesgo;

public interface RiesgoRepository extends JpaRepository<Riesgo, Long> {
	
	List<Riesgo> findByNivel(String nivel);

    List<Riesgo> findByEstado(String estado);
}
