package co.edu.uco.HumanSolution.data.factory.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.dao.*;
import co.edu.uco.HumanSolution.data.dao.entity.postgresql.*;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PostgreSqlDAOFactory extends DAOFactory {

    private Connection connection;

    // SINGLETON PATTERN
    private static PostgreSqlDAOFactory instance;

    private PostgreSqlDAOFactory() {
        // No abrir conexión en el constructor - se abrirá cuando se necesite (lazy initialization)
    }

    // MÉTODO ESTÁTICO PARA OBTENER LA INSTANCIA
    public static PostgreSqlDAOFactory getInstance() {
        if (instance == null) {
            synchronized (PostgreSqlDAOFactory.class) {
                if (instance == null) {
                    instance = new PostgreSqlDAOFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public void initTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico iniciando transacción: " + exception.getMessage(),
                    "Error al iniciar transacción",
                    exception
            );
        }
    }

    @Override
    public void commitTransaction() {
        try {
            getConnection().commit();
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico confirmando transacción: " + exception.getMessage(),
                    "Error al confirmar transacción",
                    exception
            );
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            getConnection().rollback();
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico revirtiendo transacción: " + exception.getMessage(),
                    "Error al revertir transacción",
                    exception
            );
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico cerrando conexión: " + exception.getMessage(),
                    "Error al cerrar conexión",
                    exception
            );
        }
    }

    private void openConnection() {
        // Usar las mismas credenciales que application.properties
        String url = "jdbc:postgresql://localhost:5432/sistema_usuarios";
        String user = "postgres";
        String password = "dino2020";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico abriendo conexión a base de datos: " + exception.getMessage(),
                    "Error al conectar con la base de datos",
                    exception
            );
        }
    }

    private Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                openConnection();
            }
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando conexión: " + exception.getMessage(),
                    "Error con la conexión a base de datos",
                    exception
            );
        }
        return connection;
    }

    @Override
    public TipoDocumentoDAO getTipoDocumentoDAO() {
        return new TipoDocumentoPostgreSqlDAO(getConnection());
    }

    @Override
    public TipoPermisoDAO getTipoPermisoDAO() {
        return new TipoPermisoPostgreSqlDAO(getConnection());
    }

    @Override
    public EstadoSolicitudDAO getEstadoSolicitudDAO() {
        return new EstadoSolicitudPostgreSqlDAO(getConnection());
    }

    @Override
    public TipoHoraExtraDAO getTipoHoraExtraDAO() {
        return new TipoHoraExtraPostgreSqlDAO(getConnection());
    }

    @Override
    public EstadoPuestoDAO getEstadoPuestoDAO() {
        return new EstadoPuestoPostgreSqlDAO(getConnection());
    }

    @Override
    public RolDAO getRolDAO() {
        return new RolPostgreSqlDAO(getConnection());
    }

    @Override
    public PermisoSistemaDAO getPermisoSistemaDAO() {
        return new PermisoSistemaPostgreSqlDAO(getConnection());
    }

    @Override
    public RolPermisoDAO getRolPermisoDAO() {
        return new RolPermisoPostgreSqlDAO(getConnection());
    }

    @Override
    public UnidadOrganizativaDAO getUnidadOrganizativaDAO() {
        return new UnidadOrganizativaPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioPostgreSqlDAO(getConnection());
    }

    @Override
    public PuestoDAO getPuestoDAO() {
        return new PuestoPostgreSqlDAO(getConnection());
    }

    @Override
    public ContratoDAO getContratoDAO() {
        return new ContratoPostgreSqlDAO(getConnection());
    }

    @Override
    public ExperienciaLaboralDAO getExperienciaLaboralDAO() {
        return new ExperienciaLaboralPostgreSqlDAO(getConnection());
    }

    @Override
    public EvaluacionDesempenoDAO getEvaluacionDesempenoDAO() {
        return new EvaluacionDesempenoPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioDocumentoDAO getUsuarioDocumentoDAO() {
        return new UsuarioDocumentoPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioPermisoDAO getUsuarioPermisoDAO() {
        return new UsuarioPermisoPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioHoraExtraDAO getUsuarioHoraExtraDAO() {
        return new UsuarioHoraExtraPostgreSqlDAO(getConnection());
    }
}