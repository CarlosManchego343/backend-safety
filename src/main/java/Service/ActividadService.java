package Service;

import java.util.List;

import org.springframework.stereotype.Service;

import Entity.Actividad;
import Repository.ActividadRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActividadService {

	private final ActividadRepository actividadRepository;

	public Actividad registrarActividad(Actividad actividad){
		return actividadRepository.save(actividad);
	}

	public List<Actividad> listarActividades(){
		return actividadRepository.findAll();
	}
}
