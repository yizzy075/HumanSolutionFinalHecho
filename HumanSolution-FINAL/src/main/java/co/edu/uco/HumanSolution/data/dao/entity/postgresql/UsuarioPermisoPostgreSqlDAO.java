package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.UsuarioPermisoDAO;
import co.edu.uco.HumanSolution.entity.UsuarioPermisoEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioPermisoPostgreSqlDAO implements UsuarioPermisoDAO {

    private Connection connection;

    public UsuarioPermisoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UsuarioPermisoEntity entity) {
        String sql = "INSERT INTO usuario_permiso (id, id_usuario, id_tipo_permiso, fecha_inicio, fecha_fin, id_estado_solicitud) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setObject(3, entity.getIdTipoPermiso());
            statement.setDate(4, Date.valueOf(entity.getFechaInicio()));
            statement.setDate(5, Date.valueOf(entity.getFechaFin()));
            statement.setObject(6, entity.getIdEstadoSolicitud());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando permiso de usuario",
                    "Error al crear permiso de usuario en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioPermisoEntity> read(UsuarioPermisoEntity entity) {
        List<UsuarioPermisoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, id_usuario, id_tipo_permiso, fecha_inicio, fecha_fin, id_estado_solicitud FROM usuario_permiso WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        if (!UUIDHelper.isDefault(entity.getIdTipoPermiso())) {
            sql.append(" AND id_tipo_permiso = ?");
            parametros.add(entity.getIdTipoPermiso());
        }

        if (!UUIDHelper.isDefault(entity.getIdEstadoSolicitud())) {
            sql.append(" AND id_estado_solicitud = ?");
            parametros.add(entity.getIdEstadoSolicitud());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UsuarioPermisoEntity resultado = UsuarioPermisoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            (UUID) resultSet.getObject("id_usuario"),
                            (UUID) resultSet.getObject("id_tipo_permiso"),
                            resultSet.getDate("fecha_inicio").toLocalDate(),
                            resultSet.getDate("fecha_fin").toLocalDate(),
                            (UUID) resultSet.getObject("id_estado_solicitud")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos de usuario",
                    "Error al consultar permisos de usuario",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(UsuarioPermisoEntity entity) {
        String sql = "UPDATE usuario_permiso SET id_usuario = ?, id_tipo_permiso = ?, fecha_inicio = ?, fecha_fin = ?, id_estado_solicitud = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdUsuario());
            statement.setObject(2, entity.getIdTipoPermiso());
            statement.setDate(3, Date.valueOf(entity.getFechaInicio()));
            statement.setDate(4, Date.valueOf(entity.getFechaFin()));
            statement.setObject(5, entity.getIdEstadoSolicitud());
            statement.setObject(6, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando permiso de usuario",
                    "Error al actualizar permiso de usuario",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM usuario_permiso WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando permiso de usuario",
                    "Error al eliminar permiso de usuario",
                    exception
            );
        }
    }
}