package DAO

import Model.Lista
import java.sql.SQLException

object ListasDAO {

    fun insertarLista(lista: Lista) {
        val sql = "INSERT INTO listas (nombre, id_jugador) VALUES (?, ?)"

        try {
            ConexionBD.getConnection()?.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, "nombre")
                    stmt.setString(2, "id_jugador")
                    stmt.executeUpdate()
                    println("Lista creada correctamente")
                }
            }
        }
        catch(e: SQLException){
            println("Error al crear lista")
        }
    }

    fun eliminarLista(idLista: Int){
        val sql = "delete from listas where id_lista = ?"

        try {
            ConexionBD.getConnection()?.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, idLista)

                    val filas = stmt.executeUpdate()

                    if (filas > 0){
                        println("Lista eliminada correctamente")
                    }
                    else{
                        println("No existe ninguna lista con ese ID")
                    }
                }
            }
        }
        catch(e: SQLException){
            println("Error al eliminar lista")
        }
    }
}