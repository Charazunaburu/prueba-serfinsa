package com.serfinsa.backend.util;

import com.serfinsa.backend.servicios.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SecurityUtil {

    @Autowired
    private JwtService jwtService;

    private String extractToken(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if(attributes != null){
            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader("Authorization");
            if(authHeader!= null && authHeader.startsWith("Bearer ")){
                return authHeader.substring(7);
            }
        }
        return null;
    }

    public Integer getUsuarioId(){
        String token = extractToken();
        if(token !=null){
            return jwtService.extractClaim(token, claims -> claims.get("userId", Integer.class));
        }
        return null;
    }

}