package Service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import Repository.UsuarioRepository;
import Config.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UsuarioRepository repo;
    private final JwtService jwtService;

    public String login(String email, String password) {

        var user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getContrasenia().equals(password)) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtService.generateToken(user.getCorreo());
    }
}
