package com.example.backend_safety.controller;

import org.springframework.web.bind.annotation.*;

import com.example.backend_safety.dto.LoginResponse;
import com.example.backend_safety.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	private final AuthService service;

	@PostMapping("/login")
	public LoginResponse login(@RequestParam String email,
	                           @RequestParam String password) {

	    return service.login(email, password);
	}
}
