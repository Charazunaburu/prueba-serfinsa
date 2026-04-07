package com.serfinsa.backend;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Slf4j
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BackendApplication.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        String serverPort = env.getProperty("server.port", "9090");
        String contextPath = env.getProperty("server.servlet.context-path", "/");
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("No se pudo determinar la dirección IP, usando 'localhost'");
        }

        log.info("""
            ----------------------------------------------------------
            \tAplicación '{}' está corriendo!
            \tLocal:      {}://localhost:{}
            \tExterna:    {}://{}:{}
            \tPerfil(es): {}
            ----------------------------------------------------------""",
                env.getProperty("spring.application.name", "Backend"),
                protocol, serverPort + contextPath,
                protocol, hostAddress, serverPort + contextPath,
                env.getActiveProfiles());
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .locations("classpath:db/migration")
                .schemas("prueba_backend")
                .createSchemas(true)
                .load();
    }

}

