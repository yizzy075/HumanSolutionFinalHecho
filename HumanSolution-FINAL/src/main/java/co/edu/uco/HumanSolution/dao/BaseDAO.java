package co.edu.uco.HumanSolution.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase base para todos los DAOs que utiliza DataSource para gestión de conexiones.
 * Esta implementación resuelve el problema de "Connection has been closed" al usar
 * un DataSource inyectado en lugar de gestionar conexiones manualmente.
 */
public abstract class BaseDAO {

    protected final DataSource dataSource;

    /**
     * Constructor que recibe el DataSource por inyección.
     * @param dataSource el DataSource configurado por Spring
     */
    protected BaseDAO(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource no puede ser null");
        }
        this.dataSource = dataSource;
    }

    /**
     * Obtiene una conexión del pool de conexiones del DataSource.
     * IMPORTANTE: El código que llame a este método es responsable de cerrar la conexión.
     *
     * @return una conexión válida del pool
     * @throws SQLException si hay un error al obtener la conexión
     */
    protected Connection getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        if (conn == null) {
            throw new SQLException("No se pudo obtener una conexión del DataSource");
        }
        return conn;
    }

    /**
     * Cierra la conexión de forma segura, sin lanzar excepciones.
     *
     * @param connection la conexión a cerrar
     */
    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log del error pero no lanzamos excepción
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
