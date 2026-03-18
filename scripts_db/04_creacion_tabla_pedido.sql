CREATE TABLE pedido
(
    id         INT(3) NOT NULL AUTO_INCREMENT,
    fecha      DATE DEFAULT NULL,
    forma_pago VARCHAR(15),
    cliente_id INT(3) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY        fk_cliente_id(cliente_id),
    CONSTRAINT fk_cliente_id
        FOREIGN KEY (cliente_id)
            REFERENCES cliente (id)
            ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = latin1;