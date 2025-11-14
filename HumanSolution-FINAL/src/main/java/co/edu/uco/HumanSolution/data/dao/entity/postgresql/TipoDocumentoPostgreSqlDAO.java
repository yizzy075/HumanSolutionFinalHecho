package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.TipoDocumentoDAO;
import co.edu.uco.HumanSolution.entity.TipoDocumentoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoDocumentoPostgreSqlDAO implements TipoDocumentoDAO {

    private Connection connection;

    public TipoDocumentoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoDocumentoEntity entity) {
        String sql = "INSERT INTO tipo_documento (id, nombre, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando tipo de documento",
                    "Error al crear tipo de documento en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<TipoDocumentoEntity> read(TipoDocumentoEntity entity) {
        List<TipoDocumentoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre, descripcion FROM tipo_documento WHERE 1=1");
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
                    TipoDocumentoEntity resultado = TipoDocumentoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("descripcion")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipos de documento",
                    "Error al consultar tipos de documento",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(TipoDocumentoEntity entity) {
        String sql = "UPDATE tipo_documento SET nombre = ?, descripcion = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getDescripcion());
            statement.setObject(3, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando tipo de documento",
                    "Error al actualizar tipo de documento",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM tipo_documento WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando tipo de documento",
                    "Error al eliminar tipo de documento",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM tipo_documento WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando tipo de documento",
                    "Error al verificar existencia de tipo de documento",
                    exception
            );
        }

        return false;
    }
}