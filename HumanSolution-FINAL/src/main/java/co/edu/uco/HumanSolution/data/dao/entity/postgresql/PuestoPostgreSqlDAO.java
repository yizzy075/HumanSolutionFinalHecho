package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.PuestoDAO;
import co.edu.uco.HumanSolution.entity.PuestoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PuestoPostgreSqlDAO implements PuestoDAO {

    private Connection connection;

    public PuestoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(PuestoEntity entity) {
        String sql = "INSERT INTO puesto (id, nombre, id_usuario, id_unidad, id_estado, id_jefe) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getIdUsuario());
            statement.setObject(4, entity.getIdUnidad());
            statement.setObject(5, entity.getIdEstado());

            if (UUIDHelper.isDefault(entity.getIdJefe())) {
                statement.setObject(6, null);
            } else {
                statement.setObject(6, entity.getIdJefe());
            }

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando puesto",
                    "Error al crear puesto en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<PuestoEntity> read(PuestoEntity entity) {
        List<PuestoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre, id_usuario, id_unidad, id_estado, id_jefe FROM puesto WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!entity.getNombre().isEmpty()) {
            sql.append(" AND nombre ILIKE ?");
            parametros.add("%" + entity.getNombre() + "%");
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        if (!UUIDHelper.isDefault(entity.getIdUnidad())) {
            sql.append(" AND id_unidad = ?");
            parametros.add(entity.getIdUnidad());
        }

        if (!UUIDHelper.isDefault(entity.getIdEstado())) {
            sql.append(" AND id_estado = ?");
            parametros.add(entity.getIdEstado());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID idJefe = (UUID) resultSet.getObject("id_jefe");

                    PuestoEntity resultado = PuestoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre"),
                            (UUID) resultSet.getObject("id_usuario"),
                            (UUID) resultSet.getObject("id_unidad"),
                            (UUID) resultSet.getObject("id_estado"),
                            idJefe != null ? idJefe : UUIDHelper.getDefaultUUID()
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando puestos",
                    "Error al consultar puestos",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(PuestoEntity entity) {
        String sql = "UPDATE puesto SET nombre = ?, id_usuario = ?, id_unidad = ?, id_estado = ?, id_jefe = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getIdUsuario());
            statement.setObject(3, entity.getIdUnidad());
            statement.setObject(4, entity.getIdEstado());

            if (UUIDHelper.isDefault(entity.getIdJefe())) {
                statement.setObject(5, null);
            } else {
                statement.setObject(5, entity.getIdJefe());
            }

            statement.setObject(6, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando puesto",
                    "Error al actualizar puesto",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM puesto WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando puesto",
                    "Error al eliminar puesto",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombreAndUnidad(String nombre, UUID idUnidad) {
        String sql = "SELECT COUNT(*) FROM puesto WHERE nombre = ? AND id_unidad = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setObject(2, idUnidad);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando puesto",
                    "Error al verificar existencia de puesto",
                    exception
            );
        }

        return false;
    }
}