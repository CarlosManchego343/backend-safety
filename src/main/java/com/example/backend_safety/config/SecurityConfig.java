package com.example.backend_safety.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter; // 👈 tu filtro JWT

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors()
            .and()
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // ✅ Preflight (CORS)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // ✅ Endpoints públicos típicos
                .requestMatchers("/auth/**").permitAll()

                // ✅ 👇 ESTE ES EL QUE NECESITAS
                .requestMatchers(HttpMethod.POST, "/api/evaluaciones/**").permitAll()

                // 🔒 Todo lo demás requiere JWT
                .anyRequest().authenticated()
            )
            // 👇 Se agrega el filtro JWT
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}