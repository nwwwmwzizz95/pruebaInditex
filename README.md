El propósito de este proyecto es resolver el reto planteado en la descripción, desarrollando el software necesario, explicando las decisiones que tomé a nivel técnico y proponiendo algunas ideas para posibles mejoras futuras.  
> **Nota:** Aunque el reto podría haberse resuelto con un enfoque más simple, he preferido aplicar buenas prácticas y organizar el proyecto como si fuera parte de un desarrollo real.

## Implementación

### Enfoque arquitectónico

Para el desarrollo, decidí aplicar una arquitectura basada en DDD junto con una estructura hexagonal.  
Esto me permitió separar bien las responsabilidades: el núcleo de la lógica (el dominio) no depende de nada externo, y los detalles técnicos como el acceso a datos o la API REST quedan fuera, en módulos distintos.

### Estructura general

La solución está organizada en tres módulos:

- **Dominio (pruebaInditex-domain):** contiene las reglas de negocio, entidades y lógica central.
- **Persistencia (pruebaInditex-persistence):** aquí está el acceso a la base de datos con JPA y Spring Data.
- **Aplicación (pruebaInditex-app):** expone la API REST para hacer las consultas.

La comunicación entre módulos se hace a través de interfaces (puertos), y los módulos externos se encargan de implementarlas (adaptadores).

### Desarrollo

El proyecto está hecho con **Java 17** y utiliza **Maven** como sistema de construcción.  
Todo está preparado para funcionar en contenedores, así que si se quiere ejecutar sin instalar herramientas adicionales, solo se necesita **Docker**.

Ejemplo de construcción:

```bash
mvn clean install
```
```bash
docker build -f Dockerfile . -t pruebainditex
```
```bash
docker run -p 8080:8080 pruebainditex
```

Además, el proyecto está organizado como un multi-módulo de Maven. Las dependencias entre módulos están controladas y el módulo de dominio no tiene ninguna dependencia externa, algo que se asegura con el plugin `maven-enforcer-plugin`.

## Pruebas

Para verificar el correcto funcionamiento, se han definido pruebas automatizadas que se pueden ejecutar con **Postman** y **Newman**.  
Estas pruebas llaman al servicio REST con diferentes parámetros y comprueban que las respuestas sean correctas. Toda la información está en el archivo `integration-tests/README.md`.

## Mejoras aplicadas

Además de cumplir con los requisitos del reto, se añadieron algunas mejoras:

- Pruebas unitarias aplicando enfoque TDD.
- Pruebas de integración automatizadas.
- Uso de contenedores para facilitar despliegues (por ejemplo, en Kubernetes).
- Base de datos desacoplada gracias a JPA.
- Definición del API basada en OpenAPI (enfoque API-first).
- Posibilidad de levantar un servidor mock para que otros equipos puedan avanzar sin esperar al backend.
- Uso de `docker-compose` para levantar el entorno completo fácilmente.