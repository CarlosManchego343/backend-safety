package Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.Actividad;
import Repository.ActividadRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/actividades")
@RequiredArgsConstructor
public class ActividadController {

	private final ActividadRepository repo;

    @GetMapping
    public List<Actividad> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Actividad crear(@RequestBody Actividad a) {
        return repo.save(a);
    }
}
