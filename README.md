# FinanceFlow

Aplicación fintech basada en arquitectura de microservicios desarrollada con Java y Spring Boot.

El proyecto implementa autenticación con JWT, comunicación asíncrona con RabbitMQ, Service Discovery con Eureka, API Gateway, bases de datos PostgreSQL separadas por servicio y despliegue completo con Docker.

---

# Arquitectura

## Microservicios

* AUTH-SERVICE

  * Registro y login de usuarios
  * Generación y validación de JWT
  * Publicación de eventos en RabbitMQ

* WALLET-SERVICE

  * Gestión de billeteras
  * Transferencias entre usuarios

* NOTIFICATION-SERVICE

  * Consumo de eventos desde RabbitMQ
  * Envío de emails

* API-GATEWAY

  * Punto de entrada único
  * Routing de requests
  * Validación de JWT

* EUREKA-SERVER

  * Service Discovery
  * Registro dinámico de microservicios

---

# Tecnologías

## Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Cloud Gateway
* Spring Data JPA
* Spring Cloud Netflix Eureka
* RabbitMQ
* JWT
* PostgreSQL
* Maven

## Infraestructura

* Docker
* Docker Compose

---

# Arquitectura General

```text
Cliente
   |
   v
API Gateway
   |
   +-------------------+
   |                   |
   v                   v
Auth Service      Wallet Service
   |
   v
RabbitMQ
   |
   v
Notification Service

Todos los servicios registrados en Eureka Server
```

---

# Variables importantes

## Auth Service

```properties
jwt.secret=YOUR_SECRET_KEY
```

## Notification Service

```properties
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_app_password
```

---

# Estructura del proyecto

```text
Backend/
├── auth-service
├── wallet-service
├── notification-service
├── Api-Gateway
├── Eureka-Server
├── docker/
│   └── postgres/
│       └── init.sql
└── docker-compose.yml
```

---

# Base de datos

El proyecto utiliza PostgreSQL con una base de datos independiente por microservicio.

## Bases creadas automáticamente

* auth_db
* wallet_db

## init.sql

Ubicación:

```text
/docker/postgres/init.sql
```

Contenido:

```sql
CREATE DATABASE auth_db;
CREATE DATABASE wallet_db;
```

---

# Dockerización

## Levantar el proyecto completo

Desde la carpeta Backend:

```bash
docker compose up --build
```

## Eliminar containers y volúmenes

```bash
docker compose down -v
```

---

# Servicios y puertos

| Servicio             | Puerto |
| -------------------- | ------ |
| API Gateway          | 8080   |
| Auth Service         | 8081   |
| Wallet Service       | 8082   |
| Notification Service | 8083   |
| Eureka Server        | 8761   |
| RabbitMQ             | 5672   |
| RabbitMQ Management  | 15672  |
| PostgreSQL           | 5432   |

---

# RabbitMQ

## Acceso al panel

```text
http://localhost:15672
```

Credenciales:

```text
user: guest
password: guest
```

## Queues

* cola-mails
* cola-transfers

---

# Eureka Dashboard

```text
http://localhost:8761
```

---

# Endpoints principales

## Registro

```http
POST /auth/register
```

### Body

```json
{
  "username": "usuario",
  "email": "usuario@gmail.com",
  "password": "123456"
}
```

---

## Login

```http
POST /auth/login
```

### Body

```json
{
  "email": "usuario@gmail.com",
  "password": "123456"
}
```

### Response

```json
{
  "token": "jwt_token"
}
```

---

# Flujo de autenticación

1. Usuario realiza login.
2. Auth Service valida credenciales.
3. Se genera un JWT.
4. El cliente envía el JWT en cada request.
5. API Gateway valida el token.
6. La request se redirige al microservicio correspondiente.

---

# Comunicación asíncrona

El proyecto utiliza RabbitMQ para desacoplar procesos.

## Ejemplo

1. Usuario se registra.
2. Auth Service publica un evento.
3. Notification Service consume el evento.
4. Se envía un email automáticamente.

---

# Cómo probar con Postman

## Registro

```http
POST http://localhost:8080/auth/register
```

## Login

```http
POST http://localhost:8080/auth/login
```

## Authorization

```text
Bearer Token
```

Pegar el JWT obtenido en login.

---

# Mejoras futuras

* Circuit Breaker con Resilience4j
* Config Server
* Observabilidad con Prometheus y Grafana
* Kubernetes
* CI/CD
* Refresh Tokens
* Rate Limiting
* Tests automatizados

---

# Autor

Nicolás Perlo

Técnico Universitario en Programación orientado al desarrollo backend y full stack.

* Java
* Spring Boot
* Angular
* Docker
* PostgreSQL
* RabbitMQ
* Microservicios
