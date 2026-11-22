import DAO.VideojuegoDAO
import DAO.JugadorDAO
import DAO.ListaVideojuegoDAO
import DAO.ListasDAO
import Model.Videojuego
import Model.Jugador
import Model.Lista

fun main() {
    var opcion: Int

    do {
        println("\n===== CATÁLOGO DE VIDEOJUEGOS =====")

        println("\n--- VIDEOJUEGOS ---")
        println("1. Ver todos los videojuegos")
        println("2. Buscar videojuego por ID")
        println("3. Añadir videojuego")
        println("4. Modificar videojuego")
        println("5. Eliminar videojuego")

        println("\n--- JUGADORES ---")
        println("6. Crear jugador")
        println("7. Eliminar jugador")

        println("\n--- LISTAS ---")
        println("8. Crear lista")
        println("9. Eliminar lista")
        println("10. Añadir videojuego a una lista")
        println("11. Ver videojuegos de una lista")

        println("\n0. Salir")
        println("====================================")
        print("Escoge una opción: ")

        opcion = readln().toIntOrNull() ?: -1

        when (opcion) {

            1 -> {
                val videojuegos = VideojuegoDAO.listarVideojuegos()

                if (videojuegos.isEmpty()) {
                    println("No hay videojuegos registrados.")
                } else {
                    videojuegos.forEach { println(it) }
                }
            }

            2 -> {
                print("Introduce el ID: ")
                val id = readln().toIntOrNull()

                if (id != null) {
                    val videojuego = VideojuegoDAO.consultarPorId(id)

                    if (videojuego != null) {
                        println(videojuego)
                    } else {
                        println("No se ha encontrado el videojuego.")
                    }
                } else {
                    println("ID no válido.")
                }
            }

            3 -> {
                print("Título: ")
                val titulo = readln()

                print("Género: ")
                val genero = readln()

                print("Año: ")
                val anio = readln().toIntOrNull()

                print("Nota: ")
                val nota = readln().replace(',', '.').toDoubleOrNull()

                if (titulo.isNotBlank() && genero.isNotBlank()
                    && anio != null && nota != null) {

                    val videojuego = Videojuego(
                        titulo = titulo,
                        genero = genero,
                        anio = anio,
                        nota = nota
                    )

                    VideojuegoDAO.insertarVideojuego(videojuego)
                } else {
                    println("Datos no válidos.")
                }
            }

            4 -> {
                print("ID del videojuego a actualizar: ")
                val id = readln().toIntOrNull()

                if (id != null) {
                    val videojuegoActual = VideojuegoDAO.consultarPorId(id)

                    if (videojuegoActual != null) {
                        print("Nuevo título: ")
                        val titulo = readln()

                        print("Nuevo género: ")
                        val genero = readln()

                        print("Nuevo año: ")
                        val anio = readln().toIntOrNull()

                        print("Nueva nota: ")
                        val nota = readln().replace(',', '.').toDoubleOrNull()

                        if (titulo.isNotBlank() && genero.isNotBlank()
                            && anio != null && nota != null) {

                            val videojuego = Videojuego(
                                id = id,
                                titulo = titulo,
                                genero = genero,
                                anio = anio,
                                nota = nota
                            )

                            VideojuegoDAO.actualizarVideojuego(videojuego)
                        } else {
                            println("Datos no válidos.")
                        }
                    } else {
                        println("No existe un videojuego con ese ID.")
                    }
                } else {
                    println("ID no válido.")
                }
            }

            5 -> {
                print("ID del videojuego a eliminar: ")
                val id = readln().toIntOrNull()

                if (id != null) {
                    VideojuegoDAO.eliminarVideojuego(id)
                } else {
                    println("ID no válido.")
                }
            }

            6 -> {
                println("\n--- CREAR JUGADOR ---")

                print("Nombre del jugador: ")
                val nombre = readln()

                print("Email del jugador: ")
                val email = readln()

                if (nombre.isNotBlank() && email.isNotBlank()) {
                    val jugador = Jugador(0, nombre, email)
                    JugadorDAO.insertarJugador(jugador)
                } else {
                    println("Los datos no pueden estar vacíos.")
                }
            }

            7 -> {
                println("\n--- ELIMINAR JUGADOR ---")

                print("ID del jugador a eliminar: ")
                val id = readln().toIntOrNull()

                if (id != null) {
                    JugadorDAO.eliminarJugador(id)
                } else {
                    println("ID no válido.")
                }
            }

            8 -> {
                println("\n--- CREAR LISTA ---")

                print("Nombre de la lista: ")
                val nombre = readln()

                print("ID del jugador al que pertenece: ")
                val idJugador = readln().toIntOrNull()

                if (nombre.isNotBlank() && idJugador != null) {
                    val lista = Lista(0, nombre, idJugador)
                    ListasDAO.insertarLista(lista)
                } else {
                    println("Datos no válidos.")
                }
            }

            9 -> {
                println("\n--- ELIMINAR LISTA ---")

                print("ID de la lista a eliminar: ")
                val id = readln().toIntOrNull()

                if (id != null) {
                    ListasDAO.eliminarLista(id)
                } else {
                    println("ID no válido.")
                }
            }

            10 -> {
                println("\n--- AÑADIR VIDEOJUEGO A UNA LISTA ---")

                print("ID del videojuego a añadir: ")
                val idVideojuego = readln().toIntOrNull()

                print("ID de la lista: ")
                val idLista = readln().toIntOrNull()

                print("Fecha de añadido: ")
                val fecha = readln()

                if (idVideojuego != null && idLista != null
                    && fecha.isNotBlank()) {

                    ListaVideojuegoDAO.insertar(
                        idLista,
                        idVideojuego,
                        fecha
                    )
                } else {
                    println("Datos no válidos.")
                }
            }

            11 -> {
                println("\n--- VER VIDEOJUEGOS DE UNA LISTA ---")

                print("ID de la lista: ")
                val idLista = readln().toIntOrNull()

                if (idLista != null) {
                    val videojuegos =
                        ListaVideojuegoDAO.obtenerVideojuegosDeLista(idLista)

                    if (videojuegos.isEmpty()) {
                        println("No hay videojuegos en esta lista.")
                    } else {
                        videojuegos.forEach { println(it) }
                    }
                } else {
                    println("ID no válido.")
                }
            }

            0 -> println("Saliendo del programa...")

            else -> println("Opción no válida.")
        }

    } while (opcion != 0)
}
