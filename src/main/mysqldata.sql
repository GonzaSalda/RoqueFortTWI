-- Active: 1734374796751@@127.0.0.1@3306
CREATE
DATABASE tallerweb;

USE
tallerweb;

CREATE TABLE pizza (
    Identificador BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(255),
    Precio DECIMAL(10, 2) NOT NULL,
    Imagen VARCHAR(255)
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Rol ENUM('admin', 'usuario') DEFAULT 'usuario'
);

INSERT INTO pizza(Identificador, Nombre, Descripcion, Precio, Imagen)
VALUES (1, 'MODERNA', 'Salsa, muzza, rúcula, jamón crudo, tomates deshidratados, parmesano', 3000.0, 'ALEMANA.jpg'),
       (2, 'VEGETARIANA',
        'Salsa, muzza, zucchini, asado, berenjena asada, tomates cherrys confitados, aceitunas negras, y hojas de albahaca',
        2500.0, 'VEGETARIANA.jpg'),
       (3, 'ESPECIAL DE CEBOLLA',
        'Salsa, muzzarella, cebolla cortada en pluma, tomates cherrys confitados, aceitunas negras', 2600.0,
        'ESPECIAL DE CEBOLLA.jpg'),
       (4, 'ALEMANA', 'Salsa, muzza, cebolla caramelizada, salchicha parrillera y mostaza', 2500.0, 'ALEMANA.jpg'),
       (5, 'INFERNO',
        'Salsa, muzza, panceta feteada, jalapeños verdes en rodajas, salsa de jalapeño rojo, cebolla morada, provolone rallado y hojas de albahaca',
        2800.0, 'INFERNO.jpg'),
       (6, 'FUGAZZETTA DE JAMÓN', 'Jamón, muzza, cebolla cortada en pluma', 2500.0, 'FUGAZZETTA DE JAMÓN.jpg'),
       (7, 'FUGAZZETTA DE PEPPERONI', 'Jamón, pepperoni, muzza, cebolla cortada en pluma', 2600.0,
        'FUGAZZETTA DE PEPPERONI.jpg');

INSERT INTO usuario(Nombre, Email, Password, Rol)
VALUES ('Admin', 'admin@admin.com', '123', 'admin');

INSERT INTO moto(Marca, disponible)
VALUES ('Gillera', true);
/*        ('Bajaj', true),
        ('Honda', true);*/


CREATE TABLE extras (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10, 2) NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
);
