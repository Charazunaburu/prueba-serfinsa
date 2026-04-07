package com.serfinsa.backend.controller;

import com.serfinsa.backend.dto.ApiLogin;
import com.serfinsa.backend.dto.LoginRequest;
import com.serfinsa.backend.model.Usuario;
import com.serfinsa.backend.servicios.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiLogin<Usuario, String>> Login(@RequestBody LoginRequest request){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = authService.Login(request);
        return ResponseEntity.ok(ApiLogin.success(null,token,"Login exitoso"));
    }
}

