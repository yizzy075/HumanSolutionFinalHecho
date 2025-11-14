package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.UsuarioDocumentoDAO;
import co.edu.uco.HumanSolution.entity.UsuarioDocumentoEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioDocumentoPostgreSqlDAO implements UsuarioDocumentoDAO {

    private Connection connection;

    public UsuarioDocumentoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UsuarioDocumentoEntity entity) {
        String sql = "INSERT INTO usuario_documento (id, id_usuario, id_tipo_documento, fecha, id_estado_solicitud) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setObject(3, entity.getIdTipoDocumento());
            statement.setDate(4, Date.valueOf(entity.getFecha()));
            statement.setObject(5, entity.getIdEstadoSolicitud());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando documento de usuario",
                    "Error al crear documento de usuario en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioDocumentoEntity> read(UsuarioDocumentoEntity entity) {
        List<UsuarioDocumentoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, id_usuario, id_tipo_documento, fecha, id_estado_solicitud FROM usuario_documento WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        if (!UUIDHelper.isDefault(entity.getIdTipoDocumento())) {
            sql.append(" AND id_tipo_documento = ?");
            parametros.add(entity.getIdTipoDocumento());
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
                    UsuarioDocumentoEntity resultado = UsuarioDocumentoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            (UUID) resultSet.getObject("id_usuario"),
                            (UUID) resultSet.getObject("id_tipo_documento"),
                            resultSet.getDate("fecha").toLocalDate(),
                            (UUID) resultSet.getObject("id_estado_solicitud")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando documentos de usuario",
                    "Error al consultar documentos de usuario",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(UsuarioDocumentoEntity entity) {
        String sql = "UPDATE usuario_documento SET id_usuario = ?, id_tipo_documento = ?, fecha = ?, id_estado_solicitud = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdUsuario());
            statement.setObject(2, entity.getIdTipoDocumento());
            statement.setDate(3, Date.valueOf(entity.getFecha()));
            statement.setObject(4, entity.getIdEstadoSolicitud());
            statement.setObject(5, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando documento de usuario",
                    "Error al actualizar documento de usuario",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM usuario_documento WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando documento de usuario",
                    "Error al eliminar documento de usuario",
                    exception
            );
        }
    }
}