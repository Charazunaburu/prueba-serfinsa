package com.serfinsa.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;

import java.math.BigDecimal;

@Entity
@Data
@SoftDelete(columnName = "deleted")
public class Producto extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    @Column(name = "precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;
    private Integer stock;
    @Column(name = "tipo_producto")
    private String tipoProducto;


}
