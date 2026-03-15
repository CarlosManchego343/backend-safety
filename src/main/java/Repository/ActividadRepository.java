package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.Actividad;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad,Long>{

	List<Actividad> findByTrabajadorId(Long trabajadorId);

}