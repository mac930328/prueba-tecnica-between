# Prueba tecnica Between - Spring Boot Application

## Descripción

Este proyecto es una aplicación de Spring Boot que implementa un servicio REST para consultar los precios de un producto para una marca específica en una fecha dada. Utiliza una base de datos H2 para almacenar los datos de ejemplo y está diseñado siguiendo la **arquitectura hexagonal** (puertos y adaptadores).

Al iniciar la aplicación los scripts en `src/main/resources/data.sql` se aplican para inicializar las tablas y los datos de ejemplo.

## Requisitos

- Java 17 o superior
- Maven 3.8 o superior
- Docker y Docker Compose (para ejecutar vía contenedores)

## Ejecución local (Maven)

```bash
# Compilar
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```
La aplicación estará disponible en http://localhost:8080.

## Ejecución con Docker Compose (recomendado)

El proyecto incluye un `docker-compose.yml` que levanta:
- `app` (la aplicación Spring Boot) en el puerto 8080
- `h2db` (imagen H2) en el puerto 1521
- `swagger-ui` (Swagger UI) en el puerto 8081, configurado para cargar el `openapi.yml` estático
- `jacoco-report` (servidor web que sirve el reporte JaCoCo) en el puerto 9000

Comandos útiles en PowerShell (desde la raíz del repo):

- Reconstruir todas las imágenes y levantar el stack (sin caché):
```powershell
docker-compose build --no-cache --pull; docker-compose up -d --force-recreate --remove-orphans
```

- Reconstruir solo la imagen `app` sin caché y levantarla:
```powershell
docker-compose build --no-cache app; docker-compose up -d app
```

- Recrear solo la UI de Swagger (útil si cambias `openapi.yml` o el index):
```powershell
docker-compose up -d --force-recreate --build swagger-ui
```

- Parar y eliminar los contenedores del stack:
```powershell
docker-compose down
```

## OpenAPI / Swagger

Para evitar problemas de CORS y controlar exactamente la documentación, el proyecto ahora usa un archivo OpenAPI estático:

- Archivo: `src/main/resources/static/openapi.yml`
- El archivo estático se sirve desde la aplicación en: `http://localhost:8080/openapi.yml`
- Además, el contenedor `swagger-ui` monta y sirve ese mismo archivo localmente para evitar peticiones cross-origin.

URLs útiles:
- Swagger UI (montada en el contenedor `swagger-ui`): http://localhost:8081
- OpenAPI YAML (servido por la app): http://localhost:8080/openapi.yml

Nota: la generación automática por código (springdoc) se ha desactivado y la configuración programática de OpenAPI se ha eliminado para usar esta especificación estática.

## Endpoint principal

### Consultar precio

- Endpoint: GET /api/price
- Parámetros:
  - `applicationDate`: Fecha y hora (formato yyyy-MM-ddTHH:mm:ss)
  - `productId`: Identificador del producto
  - `brandId`: Identificador de la marca

Ejemplo de petición:

```bash
curl -X GET "http://localhost:8080/api/price?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1"
```

Ejemplo de respuesta:

```json
{
  "priceList": 2,
  "productId": 35455,
  "brandId": 1,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45
}
```

## Base de datos H2

La base de datos H2 se inicializa con `src/main/resources/data.sql` al arrancar la aplicación (la propiedad `spring.sql.init.mode` está configurada para forzar la carga). Puedes acceder a la consola H2 en:

- http://localhost:8080/h2-console (cuando ejecutas la app con Maven)

Credenciales (por defecto en este proyecto):
- Si ejecutas la app con Maven (`mvn spring-boot:run`) sin configurar variables de entorno, la aplicación usa una base en memoria:
  - JDBC URL: `jdbc:h2:mem:appdb`
  - User: `SA`
  - Password: `sa`

- Si la ejecutas con Docker Compose (ver `README_DOCKER.md`), el `docker-compose.yml` inyecta la variable de entorno:
  - `JDBC_DATABASE_URL=jdbc:h2:tcp://h2db:1521/appdb`
  En ese caso, desde la consola integrada (http://localhost:8080/h2-console) utiliza:
  - JDBC URL: `jdbc:h2:tcp://h2db:1521/appdb`
  - User: `SA`
  - Password: `sa`

Notas útiles:
- Si ves el error: "Database 'C:/Users/..../appdb' not found" al intentar conectar, es porque estás usando un JDBC URL de archivo local (p.ej. `jdbc:h2:appdb`) en lugar del URL en memoria o TCP. Asegúrate de usar el URL correcto según cómo inicies la app.
- Para garantizar que `data.sql` se aplique cuando usas H2 TCP (Docker), asegúrate de que el servicio H2 esté listo antes de que la aplicación intente conectarse; `depends_on` no garantiza readiness. Añade un `healthcheck` o un pequeño script de espera si observas errores intermitentes en el arranque.

## Pruebas y cobertura

- Ejecutar tests:
```bash
mvn clean test
```

- El reporte JaCoCo se genera en `target/site/jacoco/index.html`. Si usas Docker Compose, el servicio `jacoco-report` monta `./target/site/jacoco` y lo sirve en:

- http://localhost:9000

## Notas y troubleshooting

- CORS: la UI de Swagger se sirve desde `http://localhost:8081` y está configurada para cargar el `openapi.yml` desde su propio origen (montado en el contenedor) para evitar bloqueos por CORS. Si ves errores tipo "Fetch error Failed to fetch http://app:8080/openapi.yml" asegúrate de recrear el contenedor `swagger-ui` con `--force-recreate` y limpiar caché del navegador.

- Si cambias `openapi.yml`, recrea el servicio `swagger-ui`:
```powershell
docker-compose up -d --force-recreate --build swagger-ui
```

- Si necesitas que la app genere la documentación automáticamente (springdoc), puedo revertir los cambios y volver a habilitar la dependencia/configuración.

## Repositorio y contacto

Para más información y pruebas de ejemplo revisa los tests en `src/test/java/...` y el archivo de datos `src/main/resources/data.sql`.
