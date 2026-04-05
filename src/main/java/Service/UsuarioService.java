package Service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import Model.Usuario;
import Repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repo;

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario guardar(Usuario u) {
        return repo.save(u);
    }

    public Usuario obtener(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
