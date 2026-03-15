package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entity.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte,Long>{
}