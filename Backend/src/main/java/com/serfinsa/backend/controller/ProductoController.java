package com.serfinsa.backend.controller;

import com.serfinsa.backend.dto.ApiResponse;
import com.serfinsa.backend.model.Producto;
import com.serfinsa.backend.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> getAll(){
        List<Producto> listaPaciente = productoService.getAll();
        return ResponseEntity.ok(
                ApiResponse.success(listaPaciente, "Lista de producto obtenido exitosamente.")
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Producto>> store(@RequestBody Producto producto){
        Producto productoGuardado = productoService.store(producto);
        return ResponseEntity.ok(ApiResponse.success(productoGuardado,"Producto guardado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> update(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.update(id, producto);
        return ResponseEntity.ok(
                ApiResponse.success(productoActualizado, "Producto actualizado correctamente.")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success(null, "Producto eliminado correctamente.")
        );
    }
}
