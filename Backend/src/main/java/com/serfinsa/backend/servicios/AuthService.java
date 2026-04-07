package com.serfinsa.backend.servicios;

import com.serfinsa.backend.dto.LoginRequest;
import com.serfinsa.backend.model.Usuario;
import com.serfinsa.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public String Login(LoginRequest request){
        Usuario user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("rolId", user.getRol().getId());
        claims.put("nombreRol", user.getRol().getNombre());
        claims.put("user", user.getUsername());

        return jwtService.generateToken(user.getUsername(), claims);
    }
}
