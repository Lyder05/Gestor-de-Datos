package DAO

import Model.Videojuego

object VideojuegoDAO {

fun listarVideojuegos(): List<Videojuego> {
    val lista = mutableListOf<Videojuego>()
    val sql = "select * from videojuegos"
    ConexionBD.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use { stmt ->
            val rs = stmt.executeQuery()
            while (rs.next()) {
                lista.add(Videojuego(
                    id = rs.getInt("id"),
                    titulo = rs.getString("titulo"),
                    genero = rs.getString("genero"),
                    anio = rs.getInt("anio"),
                    nota = rs.getDouble("nota"),
                ))
            }

        }
    }
    return lista
}

fun consultarPorId(id: Int): Videojuego? {
    var videojuego: Videojuego? = null
    var sql = "select * from videojuegos where id = ?"
    ConexionBD.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use { stmt ->
            stmt.setInt(1, id)
            val rs = stmt.executeQuery()
            if (rs.next()) {
                videojuego = Videojuego(
                    id = rs.getInt("id"),
                    titulo = rs.getString("titulo"),
                    genero = rs.getString("genero"),
                    anio = rs.getInt("anio"),
                    nota = rs.getDouble("nota"),
                )
            }
        }
    }
    return videojuego
}

fun insertarVideojuego(videojuego: Videojuego) {
    val sql = "insert into videojuegos(titulo, genero, anio, nota) values (?,?,?,?)"
    ConexionBD.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use { stmt ->
            stmt.setString(2, videojuego.titulo)
            stmt.setString(3, videojuego.genero)
            stmt.setInt(4, videojuego.anio)
            stmt.setDouble(5, videojuego.nota)
            if (stmt.executeUpdate() > 0) {
                println("${videojuego.titulo} insertado con éxito")
            }
        }
    }

}

fun actualizarVideojuego (videojuego: Videojuego) {
    if (videojuego.id == null) {
        println("Error: No se puede actualizar un videojuego sin ID.")
        return
    }
    val sql = "update videojuegos set titulo = ?, genero = ?, anio = ?, nota = ? where id = ?"
    ConexionBD.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use { stmt ->
            stmt.setString(1, videojuego.titulo)
            stmt.setString(2, videojuego.genero)
            stmt.setInt(3, videojuego.anio)
            stmt.setDouble(4, videojuego.nota)
            stmt.setObject(5, videojuego.id)
            if (stmt.executeUpdate() > 0) {
                println("Videojuego con ID ${videojuego.id} actualizado")
            } else {
                println("No se encontró ningún videojuego con ID ${videojuego.id}")
            }
        }
    }
}

fun eliminarVideojuego (id: Int) {
    val sql = "delete from videojuegos where id = ?"
    ConexionBD.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use { stmt ->
            stmt.setInt(1, id)
            if(stmt.executeUpdate() > 0) {
                println("Videojuego con id $id eliminado")
            }
            else{
                println("No se encontró ningún videojuego con ID $id para eliminar.")
            }
        }
    }

}

}