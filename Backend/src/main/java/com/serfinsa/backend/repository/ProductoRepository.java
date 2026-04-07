package com.serfinsa.backend.repository;

import com.serfinsa.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
