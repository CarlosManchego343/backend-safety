package Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entity.Actividad;
import Service.ActividadService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/actividades")
@RequiredArgsConstructor
public class ActividadController {
	
	private final ActividadService actividadService;

	@PostMapping
	public Actividad registrarActividad(@RequestBody Actividad actividad){
		return actividadService.registrarActividad(actividad);
	}

	@GetMapping
	public List<Actividad> listar(){
		return actividadService.listarActividades();
	}
}
