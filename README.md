# **Examen MercadoLibre**

Este proyecto es una API desarrollada en **Spring Boot** y gestionada con **Gradle**. El despliegue está realizado en **Render**. A continuación, encontrarás las instrucciones para clonar, ejecutar y probar el proyecto.

---

## **Índice**
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Ejecución Local](#ejecución-local)
- [Pruebas](#pruebas)
- [Endpoints](#endpoints)
- [URL API](#url-api)

---

## **Requisitos Previos**
- **Java 17+**
- **Gradle 8+**
- **Postman** o cualquier cliente HTTP para probar los endpoints (opcional).
- **Base de Datos H2**.
---

## **Instalación**

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Juampicosta123/Parcial1/
   cd Parcial1
   ```
2. Verifica que las dependencias se instalen correctamente:
   ```bash
   ./gradlew build
   ```
---

## **Ejecución Local**
1. Ejecuta H2.
2. Levanta la aplicación ejecutando (Estará disponible en localhost:8080):
   ```bash
   ./gradlew bootRun
   ```
---

## **Pruebas**
1. Ejecuta las pruebas automatizadas con:
   ```bash
   ./gradlew test
   ```
---

## **Endpoints**
| Método |  Endpoint  | Descripción |
|:-----|:--------:|------:|
| POST   | /api/mutant/ | Devuelve si un ADN es mutante o no |
| GET   |  /api/stats/  |   Devuelve una estadística con la cantidad de mutantes y humanos |
---
## **URL API**
[URL API EN RENDER]([https://example.com](https://parcial1-6j43.onrender.com))
---



