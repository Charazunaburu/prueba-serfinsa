package com.serfinsa.backend.servicios;

import com.serfinsa.backend.exception.ResourceNotFoundException;
import com.serfinsa.backend.model.Producto;
import com.serfinsa.backend.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAll(){
        return productoRepository.findAll();
    }

    public Producto store(Producto paciente){
        return productoRepository.save(paciente);
    }

    public Producto findById(Long id){
        return productoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado."));
    }

    @Transactional
    public Producto update(Long id, Producto detallesProducto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setDescripcion(detallesProducto.getDescripcion());
        producto.setPrecio(detallesProducto.getPrecio());
        producto.setStock(detallesProducto.getStock());

        return productoRepository.save(producto);
    }

    @Transactional
    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar: Producto no existe");
        }

        productoRepository.deleteById(id);
    }
}
