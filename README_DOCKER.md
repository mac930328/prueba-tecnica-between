# Docker Compose - Between E-commerce

Este documento describe cómo levantar la aplicación, la base de datos H2, la documentación OpenAPI (Swagger UI), y el reporte JaCoCo usando Docker Compose.

Servicios incluidos:
- app: la aplicación Spring Boot (puerto 8080)
- h2db: base de datos H2 accesible por TCP y consola web (puerto TCP 1521 y web 81)
- swagger-ui: Swagger UI apuntando a /openapi.yml (puerto 8081)
- jacoco-report: servidor HTTP simple para servir el reporte JaCoCo (puerto 9000)

Instrucciones rápidas:

1) Construir y levantar el stack:

```powershell
docker-compose up --build -d
```

2) Accesos:
- Swagger UI: http://localhost:8081
- API: http://localhost:8080/api/price
- H2 consola (acceso web ofrecido por la imagen h2 si está expuesta): http://localhost:81
- H2 TCP para la aplicación: jdbc:h2:tcp://h2db:1521/appdb
- JaCoCo report (si generaste tests localmente antes): http://localhost:9000

3) Notas:
- El Dockerfile usa Maven para compilar la JAR; la imagen `app` construirá la aplicación en la fase de build.
- El servicio `jacoco-report` sirve los archivos desde `target/site/jacoco`. Ejecuta `mvn test` localmente o en CI para generar el reporte antes de levantar ese servicio.
- Si prefieres usar PostgreSQL en lugar de H2, modifica `docker-compose.yml` y `src/main/resources/application.yml`.
