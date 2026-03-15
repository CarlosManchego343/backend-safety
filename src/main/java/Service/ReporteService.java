package Service;

import java.util.List;

import org.springframework.stereotype.Service;

import Entity.Reporte;
import Repository.ReporteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {

	private final ReporteRepository reporteRepository;

	public Reporte generarReporte(Reporte reporte){
		return reporteRepository.save(reporte);
	}

	public List<Reporte> listarReportes(){
		return reporteRepository.findAll();
	}
}
