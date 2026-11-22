import DAO.VideojuegoDAO
import Model.Videojuego

fun main() {
    var opcion: Int

    do {
        println("\n===== GESTOR DE VIDEOJUEGOS =====")
        println("1. Listar videojuegos")
        println("2. Consultar videojuego por ID")
        println("3. Insertar videojuego")
        println("4. Actualizar videojuego")
        println("5. Eliminar videojuego")
        println("6. Añadir videojuego a lista")
        println("0. Salir")
        print("Selecciona una opción: ")

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
                val nota = readln().toDoubleOrNull()

                if (anio != null && nota != null) {
                    val videojuego = Videojuego(
                        titulo = titulo,
                        genero = genero,
                        anio = anio,
                        nota = nota
                    )

                    VideojuegoDAO.insertarVideojuego(videojuego)
                    println("Operación de inserción ejecutada.")
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
                        val nota = readln().toDoubleOrNull()

                        if (anio != null && nota != null) {
                            val videojuego = Videojuego(
                                id = id,
                                titulo = titulo,
                                genero = genero,
                                anio = anio,
                                nota = nota
                            )

                            VideojuegoDAO.actualizarVideojuego(videojuego)
                            println("Operación de actualización ejecutada.")
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
                    println("Operación de eliminación ejecutada.")
                } else {
                    println("ID no válido.")
                }
            }

            6 -> {
                print("ID del videojuego a añadir: ")
                val juego: Int = readln().toInt()
                println("Numero de lista")
                val lista: Int = readln().toInt()
                println("Dia añadido")
                val fecha = readln()

                VideojuegoDAO.anyadirALista(lista,juego, fecha)

            }

            0 -> println("Saliendo del programa...")

            else -> println("Opción no válida.")
        }


    } while (opcion != 0)
}
