package com.serfinsa.backend.seeder;

import com.serfinsa.backend.model.Rol;
import com.serfinsa.backend.model.Usuario;
import com.serfinsa.backend.repository.RolRepository;
import com.serfinsa.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class UsuarioSeeder implements DataSeeder {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void seed() {
        if (usuarioRepository.findByUsername("AdminSerfinsa").isEmpty()) {
            Rol adminRol = rolRepository.findByNombre("Admin")
                    .orElseThrow(() -> new RuntimeException("Error: Rol Admin no encontrado"));

            Usuario admin = new Usuario();
            admin.setUsername("AdminSerfinsa");
            admin.setPassword(encoder.encode("Admin1234"));
            admin.setRol(adminRol);

            usuarioRepository.save(admin);
            System.out.println("Usuario Admin inicializado.");
        }

        if (usuarioRepository.findByUsername("ClienteSerfinsa").isEmpty()) {
            Rol clienteRol = rolRepository.findByNombre("Cliente")
                    .orElseThrow(() -> new RuntimeException("Error: Rol Cliente no encontrado"));

            Usuario cliente = new Usuario();
            cliente.setUsername("ClienteSerfinsa");
            cliente.setPassword(encoder.encode("Cliente1234"));
            cliente.setRol(clienteRol);

            usuarioRepository.save(cliente);
            System.out.println("Usuario Cliente inicializado.");
        }
    }
}
