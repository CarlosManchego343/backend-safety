package com.example.backend_safety.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

	 private final JwtService jwtService;

	    public JwtFilter(JwtService jwtService) {
	        this.jwtService = jwtService;
	    }

	    @Override
	    protected void doFilterInternal(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            FilterChain filterChain
	    ) throws ServletException, IOException {

	        String authHeader = request.getHeader("Authorization");

	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        try {
	            String token = authHeader.substring(7);

	            String username = jwtService.extractUsername(token);

	            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	                UsernamePasswordAuthenticationToken auth =
	                        new UsernamePasswordAuthenticationToken(
	                                username,
	                                null,
	                                Collections.emptyList()
	                        );

	                SecurityContextHolder.getContext().setAuthentication(auth);
	            }
	            
	            System.out.println("🔥 JWT FILTER EJECUTÁNDOSE");
	            System.out.println("🔥 AUTH HEADER: " + request.getHeader("Authorization"));
	            System.out.println("🔥 AUTH OBJ: " + SecurityContextHolder.getContext().getAuthentication());

	        } catch (Exception e) {
	            System.out.println("❌ JWT ERROR: " + e.getMessage());
	            SecurityContextHolder.clearContext();
	        }

	        filterChain.doFilter(request, response);
	    }
}
