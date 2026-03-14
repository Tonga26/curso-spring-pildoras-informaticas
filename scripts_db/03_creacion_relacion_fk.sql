-- =========================================================================
-- SCRIPT 03: RESTRICCIÓN DE CLAVE FORÁNEA (RELACIÓN 1 A 1)
-- =========================================================================
-- Este script altera la tabla 'cliente' para decirle a MySQL:
-- "El 'id' de esta tabla ahora está enlazado directamente al 'id' de
-- la tabla 'detalles_cliente'".
-- ON UPDATE NO ACTION / ON DELETE NO ACTION: Protege la integridad de los datos
-- impidiendo borrados accidentales desde la base de datos.
ALTER TABLE `cliente`
    ADD CONSTRAINT `fk_detalles`
    FOREIGN KEY (`id`)
    REFERENCES `detalles_cliente` (`id`)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;