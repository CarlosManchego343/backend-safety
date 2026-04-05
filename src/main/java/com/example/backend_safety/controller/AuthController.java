package com.example.backend_safety.controller;

import org.springframework.web.bind.annotation.*;

import com.example.backend_safety.service.AuthService;

import lombok.RequiredArgsConstructor;

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
