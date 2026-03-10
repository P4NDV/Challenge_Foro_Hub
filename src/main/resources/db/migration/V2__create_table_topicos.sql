CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    autor VARCHAR(120) NOT NULL,
    curso VARCHAR(120) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_topico_titulo_mensaje UNIQUE (titulo, mensaje)
);
