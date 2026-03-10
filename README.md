# Foro Hub API

API REST en Spring Boot con:
- CRUD de topicos
- Persistencia con MySQL
- Migraciones con Flyway
- Autenticacion JWT con Spring Security

## Modelo de base de datos

Tabla usuarios:
- id (PK)
- login (UNIQUE)
- clave

Tabla topicos:
- id (PK)
- titulo
- mensaje
- fecha_creacion
- status
- autor
- curso
- UNIQUE(titulo, mensaje)

## Requisitos
- Java 17
- Maven 3.9+
- MySQL 8+

## Configuracion
Editar src/main/resources/application.properties con los datos de tu base:
- spring.datasource.url
- spring.datasource.username
- spring.datasource.password
- jwt.secret
- jwt.expiration

Crear base de datos en MySQL:
CREATE DATABASE forohub;

## Ejecucion
mvn spring-boot:run

Flyway crea automaticamente las tablas y el usuario admin inicial.

Usuario inicial:
- login: admin
- clave: admin123

## Endpoints

POST /login
Body JSON:
{
  "login": "admin",
  "clave": "admin123"
}

POST /topicos
Header:
Authorization: Bearer <token>
Body JSON:
{
  "titulo": "Error JPA",
  "mensaje": "No persiste entidad",
  "autor": "Juan",
  "curso": "Spring Boot"
}

GET /topicos
Header:
Authorization: Bearer <token>

GET /topicos/{id}
Header:
Authorization: Bearer <token>

PUT /topicos/{id}
Header:
Authorization: Bearer <token>
Body JSON:
{
  "titulo": "Error JPA actualizado",
  "mensaje": "Solucionado con @Transactional",
  "autor": "Juan",
  "curso": "Spring Boot",
  "status": "RESUELTO"
}

DELETE /topicos/{id}
Header:
Authorization: Bearer <token>
