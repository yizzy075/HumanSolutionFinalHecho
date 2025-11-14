package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.UnidadOrganizativaDAO;
import co.edu.uco.HumanSolution.entity.UnidadOrganizativaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UnidadOrganizativaPostgreSqlDAO implements UnidadOrganizativaDAO {

    private Connection connection;

    public UnidadOrganizativaPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UnidadOrganizativaEntity entity) {
        String sql = "INSERT INTO unidad_organizativa (id, nombre, id_unidad_superior) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getNombre());

            if (UUIDHelper.isDefault(entity.getIdUnidadSuperior())) {
                statement.setObject(3, null);
            } else {
                statement.setObject(3, entity.getIdUnidadSuperior());
            }

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando unidad organizativa",
                    "Error al crear unidad organizativa en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<UnidadOrganizativaEntity> read(UnidadOrganizativaEntity entity) {
        List<UnidadOrganizativaEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, nombre, id_unidad_superior FROM unidad_organizativa WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!entity.getNombre().isEmpty()) {
            sql.append(" AND nombre ILIKE ?");
            parametros.add("%" + entity.getNombre() + "%");
        }

        if (!UUIDHelper.isDefault(entity.getIdUnidadSuperior())) {
            sql.append(" AND id_unidad_superior = ?");
            parametros.add(entity.getIdUnidadSuperior());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UUID idUnidadSuperior = (UUID) resultSet.getObject("id_unidad_superior");

                    UnidadOrganizativaEntity resultado = UnidadOrganizativaEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("nombre"),
                            idUnidadSuperior != null ? idUnidadSuperior : UUIDHelper.getDefaultUUID()
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando unidades organizativas",
                    "Error al consultar unidades organizativas",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(UnidadOrganizativaEntity entity) {
        String sql = "UPDATE unidad_organizativa SET nombre = ?, id_unidad_superior = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());

            if (UUIDHelper.isDefault(entity.getIdUnidadSuperior())) {
                statement.setObject(2, null);
            } else {
                statement.setObject(2, entity.getIdUnidadSuperior());
            }

            statement.setObject(3, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando unidad organizativa",
                    "Error al actualizar unidad organizativa",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM unidad_organizativa WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando unidad organizativa",
                    "Error al eliminar unidad organizativa",
                    exception
            );
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM unidad_organizativa WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando unidad organizativa",
                    "Error al verificar existencia de unidad organizativa",
                    exception
            );
        }

        return false;
    }
}