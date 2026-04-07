package com.serfinsa.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;

@Data
@Entity
@SoftDelete(columnName = "deleted")
public class Usuario extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="rol_id")
    private Rol rol;

    private String username;
    private String password;
}
