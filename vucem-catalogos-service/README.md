# VUCEM Catálogos Service

Microservicio en **Spring Boot** que centraliza y expone catálogos oficiales utilizados en la Ventanilla Única de
Comercio Exterior Mexicana (VUCEM).  
Su objetivo es garantizar **trazabilidad, consistencia y disponibilidad** de información normativa mediante una API REST
documentada con Swagger/OpenAPI.

---

## ? Características

- API REST para CRUD de catálogos.
- Persistencia con **PostgreSQL** y trazabilidad con UUID.
- Documentación automática con **Springdoc OpenAPI**.

---

## ? Estructura del proyecto

## ?? Configuración

El archivo `application.properties` contiene la configuración principal:

```properties
spring.application.name=vucem-catalogos-service

# Puerto del servidor
server.port=8092

# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://34.230.57.102:5433/postgres_restore
spring.datasource.username=userdev
spring.datasource.password=ultr4D3v202506
spring.jpa.properties.hibernate.default_schema=vucem_app_p01_normal_table
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuración JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Dialecto de PostgreSQL
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Configuración de logs
logging.level.org.springframework=INFO
logging.level.com.vucem.catalogos=DEBUG

# Swagger/OpenAPI (springdoc)
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html

? Ejecución
1. 	Clonar el repositorio:

2. 	Compilar con Maven:
mvn clean install
3. 	Ejecutar la aplicación:
mvn spring-boot:run
4. 	Acceder a la documentación Swagger:
http://localhost:8092/swagger-ui.html