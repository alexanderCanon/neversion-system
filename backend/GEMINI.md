# Documentación del Proyecto "store"

## Descripción General

Backend para ecommerce sobre cuentas digitales, se ofrecen los siguientes servicios

* Netflix Básico
* Netflix Miembro Extra
* Disney Plus
* Prime Video
* HBO Max
* Paramount Plus
* Crunchyroll
* ViX
* Spotify
* Youtube Premium

### Flujo deseado:
**Flujo cliente**

- El cliente entra al website, elige sus servicios, va al carrito y al momento de hacer su pedido se le solicita que cree su cuenta con los siguientes datos: nombre, apellido, email, contraseña y teléfono. Después de confirmar el pedido se le muestran cuentas bancarias para el depósito y un apartado para enviar la boleta; cuando envían la boleta el vendedor la recibe automáticamente y valida si el pago es válido. El pedido en espera tiene vigencia de 1 hora, después de ese lapso de tiempo se descarta. Cuando se completa el pedido se guardan los datos en una tabla en la base de datos que contiene informacion crucial tal como el usuario, fecha de compra, fecha de pago, dias restantes, si está activo o no, perfil (para cuentas compartidas), pin del perfil, el servicio que adquirió, email y contraseña de la cuenta (Netflix, Disney... etc).

La aplicación está desarrollada en Java y utiliza el framework Spring Boot. Su funcionalidad principal, por el momento, es la gestión de usuarios, permitiendo su creación y consulta.

## Tecnologías Utilizadas

*   **Java 17**: Versión del lenguaje de programación.
*   **Spring Boot**: Framework principal para el desarrollo de la aplicación.
*   **Spring Web MVC**: Para la creación de la API REST.
*   **Spring Data JPA**: Para la persistencia de datos y la comunicación con la base de datos.
*   **PostgreSQL**: Sistema de gestión de bases de datos relacional.
*   **Maven**: Herramienta para la gestión de dependencias y la construcción del proyecto.
*   **Lombok**: Librería para reducir el código repetitivo (boilerplate).

## Arquitectura del Proyecto

El proyecto sigue los principios de la **Arquitectura Hexagonal** (también conocida como arquitectura de puertos y adaptadores). Esta arquitectura permite un bajo acoplamiento entre la lógica de negocio y los componentes externos (como la base de datos o la API REST).

La estructura del proyecto se organiza de la siguiente manera:

*   **`domain`**: Contiene la lógica de negocio principal y los modelos de dominio (ej. `User.java`).
    *   **`port/in`**: Define los casos de uso de la aplicación (ej. `CreateUserUseCase.java`).
    *   **`port/out`**: Define las interfaces para la comunicación con sistemas externos (ej. `UserRepositoryPort.java`).
*   **`application`**: Contiene la implementación de los casos de uso definidos en el dominio.
*   **`infrastructure`**: Contiene la implementación de los puertos de salida y los adaptadores de entrada.
    *   **`in/rest`**: Adaptador de entrada para la API REST (`UserController.java`).
    *   **`out/persistance`**: Adaptador de salida para la base de datos (`JpaUserRepositoryAdapter.java`).

## Endpoints de la API

La aplicación expone los siguientes endpoints para la gestión de usuarios:

*   `POST /api/users`: Crea un nuevo usuario.
*   `GET /api/users/{id}`: Obtiene un usuario por su ID.

## Configuración de la Base de Datos

El archivo `application.yaml` no contiene una configuración explícita de la base de datos. Esto sugiere que la aplicación utiliza la autoconfiguración de Spring Boot, que por defecto podría usar una base de datos en memoria como H2 para el desarrollo.

Sin embargo, la presencia del driver de PostgreSQL en el archivo `pom.xml` indica que la base de datos para producción es PostgreSQL. La configuración de la conexión (URL, usuario, contraseña) debe ser proporcionada a través de variables de entorno o un archivo de configuración externo.

