import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object ConexionBD {
    private const val URL = "jdbc:postgresql://localhost:5432/videojuegos"
    private const val USER = "postgres"
    private const val PASSWORD = "*****"

    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (e: SQLException) {
            println("Error al conectar con la base de datos: ${e.message}")
            null
        }
    }

    fun testConnection(): Boolean {
        return getConnection()?.use { conn ->
            println("Conexión establecida con éxito a videojuegos")
            true
        } ?: false
    }

    fun closeConnection(conn: Connection?) {
        try {
            conn?.close()
            println("Conexión cerrada correctamente.")
        } catch (e: SQLException) {
            println("Error al cerrar la conexión: ${e.message}")
        }
    }
}