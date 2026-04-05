package com.example.backend_safety.service;

import org.springframework.stereotype.Service;

import com.example.backend_safety.model.Actividad;
import com.example.backend_safety.model.EvaluacionRiesgo;
import com.example.backend_safety.model.Riesgo;
import com.example.backend_safety.repository.EvaluacionRiesgoRepository;
import com.example.backend_safety.repository.RiesgoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiesgoService {

	private final RiesgoRepository riesgoRepo;
    private final EvaluacionRiesgoRepository evalRepo;

    public EvaluacionRiesgo evaluarRiesgo(Long riesgoId, int probabilidad, int impacto) {

        Riesgo riesgo = riesgoRepo.findById(riesgoId)
                .orElseThrow(() -> new RuntimeException("Riesgo no encontrado"));

        int valor = probabilidad * impacto;

        String nivel;
        if (valor <= 5) nivel = "BAJO";
        else if (valor <= 15) nivel = "MEDIO";
        else nivel = "ALTO";

        EvaluacionRiesgo eval = new EvaluacionRiesgo();
        eval.setProbabilidad(probabilidad);
        eval.setImpacto(impacto);
        eval.setNivelCalculado(nivel);
        eval.setRiesgo(riesgo);

        evalRepo.save(eval);

        // 🔥 actualizar riesgo
        riesgo.setNivel(nivel);
        riesgoRepo.save(riesgo);

        return eval;
    }
    
    public boolean puedeEjecutarse(Actividad actividad) {

        for (Riesgo r : actividad.getRiesgos()) {
            if ("ALTO".equals(r.getNivel())) {
                return false;
            }
        }

        return true;
    }
    
    public String generarAlerta(Riesgo riesgo) {

        if ("ALTO".equals(riesgo.getNivel())) {
            return "⚠️ ALERTA: Riesgo alto detectado. No se recomienda ejecutar la actividad.";
        }

        if ("MEDIO".equals(riesgo.getNivel())) {
            return "⚠️ Precaución: Riesgo medio.";
        }

        return "✅ Riesgo bajo.";
    }
}
