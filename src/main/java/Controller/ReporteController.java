package Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entity.Reporte;
import Service.ReporteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {
	
	private final ReporteService reporteService;

	@GetMapping
	public List<Reporte> listar(){
		return reporteService.listarReportes();
	}

	@PostMapping
	public Reporte generar(@RequestBody Reporte reporte){
		return reporteService.generarReporte(reporte);
	}
}
