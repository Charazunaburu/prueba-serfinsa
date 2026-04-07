package com.serfinsa.backend.seeder;

import com.serfinsa.backend.model.Rol;
import com.serfinsa.backend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1) // Se ejecuta primero
public class RolSeeder implements DataSeeder {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public void seed() {
        if (rolRepository.count() == 0) {
            List<String> roles = List.of("Admin", "Cliente");
            roles.forEach(nombre -> {
                Rol rol = new Rol();
                rol.setNombre(nombre);
                rol.setDescripcion("Rol con permisos de " + nombre);
                rolRepository.save(rol);
            });
            System.out.println("Roles inicializados.");
        }
    }
}
