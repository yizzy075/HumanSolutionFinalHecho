package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.PermisoSistemaDAO;
import co.edu.uco.HumanSolution.entity.PermisoSistemaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermisoSistemaPostgreSqlDAO implements PermisoSistemaDAO {

    private Connection connection;

    public PermisoSistemaPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(PermisoSistemaEntity entity) {
        String sql = "INSERT INTO permiso_sistema (id, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando permiso de sistema",
                    "Error al crear permiso de sistema en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<PermisoSistemaEntity> read(PermisoSistemaEntity entity) {
        List<PermisoSistemaEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre FROM permiso_sistema WHERE 1=1");
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
                    PermisoSistemaEntity resultado = PermisoSistemaEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos de sistema",
                    "Error al consultar permisos de sistema",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(PermisoSistemaEntity entity) {
        String sql = "UPDATE permiso_sistema SET nombre = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando permiso de sistema",
                    "Error al actualizar permiso de sistema",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM permiso_sistema WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando permiso de sistema",
                    "Error al eliminar permiso de sistema",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM permiso_sistema WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando permiso de sistema",
                    "Error al verificar existencia de permiso de sistema",
                    exception
            );
        }

        return false;
    }
}