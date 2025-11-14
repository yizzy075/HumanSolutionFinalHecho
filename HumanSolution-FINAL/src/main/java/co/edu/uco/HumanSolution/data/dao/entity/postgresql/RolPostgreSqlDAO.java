package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.RolDAO;
import co.edu.uco.HumanSolution.entity.RolEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RolPostgreSqlDAO implements RolDAO {

    private Connection connection;

    public RolPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(RolEntity entity) {
        String sql = "INSERT INTO rol (id, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando rol en base de datos: " + exception.getMessage(),
                    "Error al crear rol",
                    exception
            );
        }
    }

    @Override
    public List<RolEntity> read(RolEntity entity) {
        List<RolEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre FROM rol WHERE 1=1");
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
                    RolEntity resultado = RolEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando roles: " + exception.getMessage(),
                    "Error al consultar roles",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(RolEntity entity) {
        String sql = "UPDATE rol SET nombre = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando rol: " + exception.getMessage(),
                    "Error al actualizar rol",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM rol WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando rol: " + exception.getMessage(),
                    "Error al eliminar rol",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM rol WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando rol: " + exception.getMessage(),
                    "Error al verificar existencia de rol",
                    exception
            );
        }

        return false;
    }
}