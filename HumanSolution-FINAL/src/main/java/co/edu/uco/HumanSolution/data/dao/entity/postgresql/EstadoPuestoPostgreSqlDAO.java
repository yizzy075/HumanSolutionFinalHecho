package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.EstadoPuestoDAO;
import co.edu.uco.HumanSolution.entity.EstadoPuestoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstadoPuestoPostgreSqlDAO implements EstadoPuestoDAO {

    private Connection connection;

    public EstadoPuestoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EstadoPuestoEntity entity) {
        String sql = "INSERT INTO estado_puesto (id, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando estado de puesto",
                    "Error al crear estado de puesto en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<EstadoPuestoEntity> read(EstadoPuestoEntity entity) {
        List<EstadoPuestoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre FROM estado_puesto WHERE 1=1");
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
                    EstadoPuestoEntity resultado = EstadoPuestoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estados de puesto",
                    "Error al consultar estados de puesto",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(EstadoPuestoEntity entity) {
        String sql = "UPDATE estado_puesto SET nombre = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando estado de puesto",
                    "Error al actualizar estado de puesto",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM estado_puesto WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando estado de puesto",
                    "Error al eliminar estado de puesto",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM estado_puesto WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando estado de puesto",
                    "Error al verificar existencia de estado de puesto",
                    exception
            );
        }

        return false;
    }
}