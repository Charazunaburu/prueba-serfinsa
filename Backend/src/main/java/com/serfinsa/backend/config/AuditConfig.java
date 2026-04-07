package com.serfinsa.backend.config;

import com.serfinsa.backend.util.SecurityUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider") // Es el metodo que se crea para obtener los datos del usuario
public class AuditConfig {
    @Bean
    public AuditorAware<Integer> auditorProvider(SecurityUtil securityUtil) {
        return () -> Optional.ofNullable(securityUtil.getUsuarioId());
    }
}