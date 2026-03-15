package Controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Dto.LoginRequest;
import Dto.LoginResponse;
import Security.JwtService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request){

	authenticationManager.authenticate(
	new UsernamePasswordAuthenticationToken(
	request.getCorreo(),
	request.getPassword()
	)
	);

	String token = jwtService.generateToken(request.getCorreo());
		return new LoginResponse(token);
	}
}
