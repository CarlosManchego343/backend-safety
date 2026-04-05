package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Model.Incidente;

public interface IncidenteRepository extends JpaRepository<Incidente, Long> {

}
