# Diagrama de Base de Datos - Foro Hub

## Modelo principal

USUARIOS
- id: BIGINT, PK, autoincrement
- login: VARCHAR(100), NOT NULL, UNIQUE
- clave: VARCHAR(255), NOT NULL

TOPICOS
- id: BIGINT, PK, autoincrement
- titulo: VARCHAR(255), NOT NULL
- mensaje: TEXT, NOT NULL
- fecha_creacion: DATETIME, NOT NULL
- status: VARCHAR(30), NOT NULL
- autor: VARCHAR(120), NOT NULL
- curso: VARCHAR(120), NOT NULL
- UNIQUE(titulo, mensaje)

## Relacion actual

Para este challenge, autor y curso se almacenan como texto dentro de topicos.
No existe clave foranea obligatoria hacia otra tabla.

## Extension sugerida (opcional)

Puedes normalizar despues con tablas:
- autores
- cursos

y convertir topicos.autor y topicos.curso en llaves foraneas.
