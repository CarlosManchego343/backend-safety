package Controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import Service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService service;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        return service.login(email, password);
    }
}
