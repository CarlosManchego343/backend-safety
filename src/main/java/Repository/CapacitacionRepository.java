package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entity.Capacitacion;

@Repository
public interface CapacitacionRepository extends JpaRepository<Capacitacion,Long>{
}