package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.TipoPermisoDAO;
import co.edu.uco.HumanSolution.entity.TipoPermisoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoPermisoPostgreSqlDAO implements TipoPermisoDAO {

    private Connection connection;

    public TipoPermisoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoPermisoEntity entity) {
        String sql = "INSERT INTO tipo_permiso (id, nombre, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando tipo de permiso",
                    "Error al crear tipo de permiso en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<TipoPermisoEntity> read(TipoPermisoEntity entity) {
        List<TipoPermisoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre, descripcion FROM tipo_permiso WHERE 1=1");
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
                    TipoPermisoEntity resultado = TipoPermisoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("descripcion")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipos de permiso",
                    "Error al consultar tipos de permiso",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(TipoPermisoEntity entity) {
        String sql = "UPDATE tipo_permiso SET nombre = ?, descripcion = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getDescripcion());
            statement.setObject(3, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando tipo de permiso",
                    "Error al actualizar tipo de permiso",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM tipo_permiso WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando tipo de permiso",
                    "Error al eliminar tipo de permiso",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM tipo_permiso WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando tipo de permiso",
                    "Error al verificar existencia de tipo de permiso",
                    exception
            );
        }

        return false;
    }
}