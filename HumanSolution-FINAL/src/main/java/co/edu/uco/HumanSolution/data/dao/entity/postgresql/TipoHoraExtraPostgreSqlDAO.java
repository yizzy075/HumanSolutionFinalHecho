package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.TipoHoraExtraDAO;
import co.edu.uco.HumanSolution.entity.TipoHoraExtraEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoHoraExtraPostgreSqlDAO implements TipoHoraExtraDAO {

    private Connection connection;

    public TipoHoraExtraPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoHoraExtraEntity entity) {
        String sql = "INSERT INTO tipo_hora_extra (id, nombre, recargo) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setInt(3, entity.getRecargo());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando tipo de hora extra",
                    "Error al crear tipo de hora extra en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<TipoHoraExtraEntity> read(TipoHoraExtraEntity entity) {
        List<TipoHoraExtraEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre, recargo FROM tipo_hora_extra WHERE 1=1");
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
                    TipoHoraExtraEntity resultado = TipoHoraExtraEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre"),
                            resultSet.getInt("recargo")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipos de hora extra",
                    "Error al consultar tipos de hora extra",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(TipoHoraExtraEntity entity) {
        String sql = "UPDATE tipo_hora_extra SET nombre = ?, recargo = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setInt(2, entity.getRecargo());
            statement.setObject(3, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando tipo de hora extra",
                    "Error al actualizar tipo de hora extra",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM tipo_hora_extra WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando tipo de hora extra",
                    "Error al eliminar tipo de hora extra",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM tipo_hora_extra WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando tipo de hora extra",
                    "Error al verificar existencia de tipo de hora extra",
                    exception
            );
        }

        return false;
    }
}