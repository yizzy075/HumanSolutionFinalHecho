package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.UsuarioHoraExtraDAO;
import co.edu.uco.HumanSolution.entity.UsuarioHoraExtraEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioHoraExtraPostgreSqlDAO implements UsuarioHoraExtraDAO {

    private final Connection connection;

    public UsuarioHoraExtraPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UsuarioHoraExtraEntity entity) {
        String sql = "INSERT INTO usuario_hora_extra (id, id_usuario, fecha, id_estado_solicitud, horas, id_tipo_hora_extra) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setDate(3, Date.valueOf(entity.getFecha()));
            statement.setObject(4, entity.getIdEstadoSolicitud());
            statement.setInt(5, entity.getHoras());
            statement.setObject(6, entity.getIdTipoHoraExtra());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando hora extra de usuario",
                    "Error al crear hora extra de usuario en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioHoraExtraEntity> read(UsuarioHoraExtraEntity entity) {
        List<UsuarioHoraExtraEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, id_usuario, fecha, id_estado_solicitud, horas, id_tipo_hora_extra FROM usuario_hora_extra WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        if (!UUIDHelper.isDefault(entity.getIdEstadoSolicitud())) {
            sql.append(" AND id_estado_solicitud = ?");
            parametros.add(entity.getIdEstadoSolicitud());
        }

        if (!UUIDHelper.isDefault(entity.getIdTipoHoraExtra())) {
            sql.append(" AND id_tipo_hora_extra = ?");
            parametros.add(entity.getIdTipoHoraExtra());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UsuarioHoraExtraEntity resultado = UsuarioHoraExtraEntity.create(
                            (UUID) resultSet.getObject("id"),
                            (UUID) resultSet.getObject("id_usuario"),
                            resultSet.getDate("fecha").toLocalDate(),
                            (UUID) resultSet.getObject("id_estado_solicitud"),
                            resultSet.getInt("horas"),
                            (UUID) resultSet.getObject("id_tipo_hora_extra")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando horas extra de usuario",
                    "Error al consultar horas extra de usuario",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(UsuarioHoraExtraEntity entity) {
        String sql = "UPDATE usuario_hora_extra SET id_usuario = ?, fecha = ?, id_estado_solicitud = ?, horas = ?, id_tipo_hora_extra = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdUsuario());
            statement.setDate(2, Date.valueOf(entity.getFecha()));
            statement.setObject(3, entity.getIdEstadoSolicitud());
            statement.setInt(4, entity.getHoras());
            statement.setObject(5, entity.getIdTipoHoraExtra());
            statement.setObject(6, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando hora extra de usuario",
                    "Error al actualizar hora extra de usuario",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM usuario_hora_extra WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando hora extra de usuario",
                    "Error al eliminar hora extra de usuario",
                    exception
            );
        }
    }
}