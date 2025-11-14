package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.RolPermisoDAO;
import co.edu.uco.HumanSolution.entity.RolPermisoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RolPermisoPostgreSqlDAO implements RolPermisoDAO {

    private Connection connection;

    public RolPermisoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(RolPermisoEntity entity) {
        String sql = "INSERT INTO rol_permiso (id, id_rol, id_permiso) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdRol());
            statement.setObject(3, entity.getIdPermiso());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando relación rol-permiso",
                    "Error al crear relación rol-permiso en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<RolPermisoEntity> read(RolPermisoEntity entity) {
        List<RolPermisoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, id_rol, id_permiso FROM rol_permiso WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdRol())) {
            sql.append(" AND id_rol = ?");
            parametros.add(entity.getIdRol());
        }

        if (!UUIDHelper.isDefault(entity.getIdPermiso())) {
            sql.append(" AND id_permiso = ?");
            parametros.add(entity.getIdPermiso());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RolPermisoEntity resultado = RolPermisoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            (UUID) resultSet.getObject("id_rol"),
                            (UUID) resultSet.getObject("id_permiso")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando relaciones rol-permiso",
                    "Error al consultar relaciones rol-permiso",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM rol_permiso WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando relación rol-permiso",
                    "Error al eliminar relación rol-permiso",
                    exception
            );
        }
    }

    @Override
    public boolean existsByRolAndPermiso(UUID idRol, UUID idPermiso) {
        String sql = "SELECT COUNT(*) FROM rol_permiso WHERE id_rol = ? AND id_permiso = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, idRol);
            statement.setObject(2, idPermiso);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando relación rol-permiso",
                    "Error al verificar existencia de relación rol-permiso",
                    exception
            );
        }

        return false;
    }
}