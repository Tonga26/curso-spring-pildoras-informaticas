-- =========================================================================
-- SCRIPT 02: CREACIÓN DE TABLAS INDEPENDIENTES
-- =========================================================================
-- Creación de la tabla principal.
CREATE TABLE `cliente` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(128) DEFAULT NULL,
    `apellido` VARCHAR(128) DEFAULT NULL,
    `direccion` VARCHAR(128) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Creamos la tabla secundaria
CREATE TABLE `detalles_cliente` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `web` VARCHAR(128) DEFAULT NULL,
    `telefono` VARCHAR(128) DEFAULT NULL,
    `comentarios` VARCHAR(128) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;