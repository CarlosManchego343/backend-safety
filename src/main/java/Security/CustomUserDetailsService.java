package Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Entity.Usuario;
import Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService  {

	private final UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String correo)
	throws UsernameNotFoundException {

	Usuario usuario = usuarioRepository
	.findByCorreo(correo)
	.orElseThrow();

	return User.builder()
	.username(usuario.getCorreo())
	.password(usuario.getPassword())
	.roles(usuario.getRol())
	.build();

	}
}
