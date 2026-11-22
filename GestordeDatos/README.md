
# Catálogo de Videojuegos con Base de Datos Relacional

## Descripción

Este proyecto consiste en una aplicación que permite gestionar un catálogo de videojuegos mediante una base de datos PostgreSQL.

La aplicación utiliza el patrón DAO para separar las operaciones de acceso a datos del resto del programa.

Además de gestionar videojuegos, permite crear jugadores, asignarles listas y añadir videojuegos a dichas listas.

## Estructura de la Base de Datos

La base de datos se llama `videojuegos` y contiene cuatro tablas:

### videojuegos

Almacena la información de los videojuegos.

- id: identificador único (PK).
- titulo: título del videojuego.
- genero: género del videojuego.
- anio: año de lanzamiento.
- nota: valoración del videojuego.

### jugadores

Almacena la información de los jugadores.

- id_jugador: identificador único (PK).
- nombre: nombre del jugador.
- email: correo electrónico del jugador.

### listas

Almacena las listas creadas por los jugadores.

- id_lista: identificador único (PK).
- nombre: nombre de la lista.
- id_jugador: clave foránea (FK) que referencia a jugadores.

### listas_videojuegos

Relaciona las listas con los videojuegos.

- id_lista: clave foránea (FK) hacia listas.
- id_videojuego: clave foránea (FK) hacia videojuegos.
- fecha_añadido: fecha en la que se añadió el videojuego.

## Relaciones entre las tablas

La base de datos está formada por cuatro tablas relacionadas mediante claves primarias (PK) y claves foráneas (FK).

- jugadores → listas: un jugador puede tener varias listas, pero cada lista pertenece a un único jugador.

- listas → listas_videojuegos: una lista puede contener varios videojuegos.

- videojuegos → listas_videojuegos: un videojuego puede pertenecer a varias listas.

Para mantener la integridad referencial, he utilizado ON DELETE CASCADE, de forma que al eliminar un registro también se eliminan sus relaciones correspondientes.

## Script SQL

El diseño completo de la base de datos, incluyendo las instrucciones CREATE TABLE, las claves primarias y foráneas y los datos iniciales, se encuentra en el archivo datos/videojuegos.sql.
## Estructura del Proyecto

```text
src/main/kotlin/
    ConexionBD.kt
    Main.kt
    Model/
        Videojuego.kt
        Jugador.kt
        Lista.kt
        ListaVideojuego.kt
    DAO/
        VideojuegoDAO.kt
        JugadorDAO.kt
        ListaDAO.kt
        ListaVideojuegoDAO.kt

datos/
    videojuegos.sql

LEEME.md
```

## Instrucciones de Ejecución

### Requisitos

- IntelliJ IDEA.
- Kotlin y JDK.
- PostgreSQL.
- Driver JDBC de PostgreSQL.

### Configurar la base de datos

1. Abrir pgAdmin.
2. Crear una base de datos llamada `videojuegos`.
3. Ejecutar el archivo `datos/videojuegos.sql` sobre una base de datos vacía.
4. Comprobar que se han creado las cuatro tablas.

### Configurar la conexión

En `ConexionBD.kt` se deben configurar:

- URL: `jdbc:postgresql://localhost:5432/videojuegos`
- Usuario: `postgres`
- Contraseña: la correspondiente a la instalación local.

No se debe incluir una contraseña real en la documentación ni en el repositorio.

### Ejecutar la aplicación

1. Abrir el proyecto en IntelliJ.
2. Comprobar que PostgreSQL está iniciado.
3. Ejecutar `Main.kt`.
4. Seleccionar una opción del menú.

## Funcionalidades

### Videojuegos

- Listar videojuegos.
- Buscar por ID.
- Insertar videojuegos.
- Actualizar videojuegos.
- Eliminar videojuegos.

### Jugadores

- Crear jugadores.
- Eliminar jugadores.

### Listas

- Crear listas asociadas a jugadores.
- Eliminar listas.
- Añadir videojuegos a listas.
- Consultar los videojuegos de una lista.

## Decisiones de Diseño

He utilizado PostgreSQL como sistema gestor de bases de datos y Kotlin para desarrollar la aplicación.

He organizado el acceso a la base de datos utilizando el patrón DAO, creando una clase para cada entidad.

Para relacionar las listas y los videojuegos he utilizado una tabla intermedia llamada `listas_videojuegos`.

También he utilizado transacciones con `commit()` y `rollback()` para evitar que se guarden operaciones incompletas.

Por último, he configurado las claves foráneas con `ON DELETE CASCADE` para mantener la integridad de los datos cuando se eliminan jugadores, listas o videojuegos.
