package DAO

import Model.Jugador

object JugadorDAO {

    fun insertarJugador(jugador: Jugador) {
        val sql = "insert into jugadores (nombre,email) values (?,?)"

        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, jugador.nombre)
                stmt.setString(2, jugador.email)
                stmt.executeUpdate()
                println("Jugador insertado correctamente")
            }
        }
    }

    fun eliminarJugador(idJugador: Int) {
        val sql = "delete from jugadores where id_jugador = ?"

        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, idJugador)

                val filas = stmt.executeUpdate()

                if (filas > 0){
                    println("Jugador eliminado correctamente")
                }
                else{
                    println("No se ha encontrado ningún jugador con ese id")
                }
            }
        }
    }
}