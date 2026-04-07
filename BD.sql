CREATE DATABASE IF NOT EXISTS prueba_backend;

CREATE TABLE IF NOT EXISTS rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_by BIGINT,
    update_by BIGINT,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol_id BIGINT NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_by BIGINT,
    update_by BIGINT,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
    );

    CREATE TABLE IF NOT EXISTS producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    tipo_producto VARCHAR(250),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_by BIGINT,
    update_by BIGINT,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

INSERT IGNORE INTO roles (id, nombre, descripcion, deleted, create_at) 
VALUES 
(1, 'ADMIN', 'Administrador total del sistema', false, CURRENT_TIMESTAMP),
(2, 'CLIENTE', 'Usuario final con permisos limitados', false, CURRENT_TIMESTAMP);


INSERT IGNORE INTO usuarios (username, password, rol_id, deleted, create_at)
VALUES 
(
    'AdminSerfinsa', 
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 
    1, 
    false, 
    CURRENT_TIMESTAMP
),
(
    'ClienteSerfinsa', 
    '$2a$10$vI8BshWvY/S89jXf.M.K1.XvRzK3.mO4H4H8H4H4H4H4H4H4H4H4H', 
    2, 
    false, 
    CURRENT_TIMESTAMP
);