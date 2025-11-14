package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.EstadoSolicitudDAO;
import co.edu.uco.HumanSolution.entity.EstadoSolicitudEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstadoSolicitudPostgreSqlDAO implements EstadoSolicitudDAO {

    private Connection connection;

    public EstadoSolicitudPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EstadoSolicitudEntity entity) {
        String sql = "INSERT INTO estado_solicitud (id, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando estado de solicitud",
                    "Error al crear estado de solicitud en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<EstadoSolicitudEntity> read(EstadoSolicitudEntity entity) {
        List<EstadoSolicitudEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre FROM estado_solicitud WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!entity.getNombre().isEmpty()) {
            sql.append(" AND nombre ILIKE ?");
            parametros.add("%" + entity.getNombre() + "%");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EstadoSolicitudEntity resultado = EstadoSolicitudEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estados de solicitud",
                    "Error al consultar estados de solicitud",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(EstadoSolicitudEntity entity) {
        String sql = "UPDATE estado_solicitud SET nombre = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando estado de solicitud",
                    "Error al actualizar estado de solicitud",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM estado_solicitud WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando estado de solicitud",
                    "Error al eliminar estado de solicitud",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM estado_solicitud WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando estado de solicitud",
                    "Error al verificar existencia de estado de solicitud",
                    exception
            );
        }

        return false;
    }
}