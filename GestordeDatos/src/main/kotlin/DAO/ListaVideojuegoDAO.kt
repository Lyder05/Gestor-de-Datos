package DAO

import Model.Videojuego
import java.sql.SQLException

object ListaVideojuegoDAO {

    fun insertar(idLista: Int, idVideojuego: Int, fecha: String) {
        val conn = ConexionBD.getConnection()?:return

        try {
            val sqlSelect = "SELECT id FROM videojuegos WHERE id = ?"

            conn.prepareStatement(sqlSelect).use { stmt ->
                stmt.setInt(1,idVideojuego)

                val rs = stmt.executeQuery()
                if (!rs.next()) {
                    throw Exception("El videojuego no existe")
                }
            }

            val sqlInsert = "INSERT INTO listas_videojuegos (id_lista, id_videojuego, fecha_añadido) VALUES (?, ?, ?)"

            conn.prepareStatement(sqlInsert).use { stmt ->
                stmt.setInt(1,idLista)
                stmt.setInt(2,idVideojuego)
                stmt.setString(3,fecha)
                stmt.executeUpdate()
            }

            conn.commit()
            println("Videojuego añadido a la lista correctamente.")
        }
        catch(e: SQLException){
            conn.rollback()
            println("Error en la transacción")
            println("Transacción revertida")
        }
        finally{
            conn.close()
        }
    }

    fun obtenerVideojuegosDeLista(idLista: Int): List<Videojuego>{

        val videojuegos = mutableListOf<Videojuego>()

        val sql = "select v.id, v.titulo, v.genero, v.anio, v.nota" +
                " from videojuegos v " +
                "inner join listas_videojuegos lv " +
                "   on v.id = lv.id_videojuego " +
                "where lv.id_lista = ?"

        ConexionBD.getConnection()?.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setInt(1,idLista)

                val rs = stmt.executeQuery()

                while (rs.next()) {
                    val videojuego = Videojuego(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("fecha"),
                        rs.getInt("anio"),
                        rs.getDouble("nota")
                    )
                    videojuegos.add(videojuego)
                }
            }
        }

        return videojuegos
    }



}