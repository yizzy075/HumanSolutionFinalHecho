package co.edu.uco.HumanSolution.crosscutting.helper;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlConnectionHelper {

    private SqlConnectionHelper() {
        super();
    }

    public static boolean connectionIsNull(Connection connection) {
        return ObjectHelper.isNull(connection);
    }

    public static boolean connectionIsOpen(Connection connection) {
        try {
            return !connectionIsNull(connection) && !connection.isClosed();
        } catch (SQLException exception) {
            return false;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connectionIsOpen(connection)) {
                connection.close();
            }
        } catch (SQLException exception) {
            // Log exception
        }
    }

    public static void initTransaction(Connection connection) {
        try {
            if (connectionIsOpen(connection)) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("No se pudo iniciar la transacción", exception);
        }
    }

    public static void commitTransaction(Connection connection) {
        try {
            if (connectionIsOpen(connection)) {
                connection.commit();
            }
        } catch (SQLException exception) {
            throw new RuntimeException("No se pudo hacer commit de la transacción", exception);
        }
    }

    public static void rollbackTransaction(Connection connection) {
        try {
            if (connectionIsOpen(connection)) {
                connection.rollback();
            }
        } catch (SQLException exception) {
            throw new RuntimeException("No se pudo hacer rollback de la transacción", exception);
        }
    }
}