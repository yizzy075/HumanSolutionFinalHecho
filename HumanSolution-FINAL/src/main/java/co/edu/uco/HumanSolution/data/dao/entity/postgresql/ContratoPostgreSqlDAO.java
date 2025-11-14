package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.ContratoDAO;
import co.edu.uco.HumanSolution.entity.ContratoEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContratoPostgreSqlDAO implements ContratoDAO {

    private Connection connection;

    public ContratoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ContratoEntity entity) {
        String sql = "INSERT INTO contrato (id, id_usuario, fecha_inicio, fecha_fin, sueldo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setDate(3, Date.valueOf(entity.getFechaInicio()));

            if (entity.getFechaFin() != null) {
                statement.setDate(4, Date.valueOf(entity.getFechaFin()));
            } else {
                statement.setDate(4, null);
            }

            statement.setBigDecimal(5, entity.getSueldo());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando contrato",
                    "Error al crear contrato en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<ContratoEntity> read(ContratoEntity entity) {
        List<ContratoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, id_usuario, fecha_inicio, fecha_fin, sueldo FROM contrato WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Date fechaFin = resultSet.getDate("fecha_fin");

                    ContratoEntity resultado = ContratoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            (UUID) resultSet.getObject("id_usuario"),
                            resultSet.getDate("fecha_inicio").toLocalDate(),
                            fechaFin != null ? fechaFin.toLocalDate() : null,
                            resultSet.getBigDecimal("sueldo")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando contratos",
                    "Error al consultar contratos",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(ContratoEntity entity) {
        String sql = "UPDATE contrato SET id_usuario = ?, fecha_inicio = ?, fecha_fin = ?, sueldo = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdUsuario());
            statement.setDate(2, Date.valueOf(entity.getFechaInicio()));

            if (entity.getFechaFin() != null) {
                statement.setDate(3, Date.valueOf(entity.getFechaFin()));
            } else {
                statement.setDate(3, null);
            }

            statement.setBigDecimal(4, entity.getSueldo());
            statement.setObject(5, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando contrato",
                    "Error al actualizar contrato",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM contrato WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando contrato",
                    "Error al eliminar contrato",
                    exception
            );
        }
    }

    @Override
    public boolean existsContratoVigenteByUsuario(UUID idUsuario) {
        String sql = "SELECT COUNT(*) FROM contrato WHERE id_usuario = ? AND (fecha_fin IS NULL OR fecha_fin >= CURRENT_DATE)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando contrato vigente",
                    "Error al verificar existencia de contrato vigente",
                    exception
            );
        }

        return false;
    }

    @Override
    public boolean existsContratoVigenteByUsuarioAndFecha(UUID idUsuario, java.time.LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM contrato " +
                     "WHERE id_usuario = ? " +
                     "AND fecha_inicio <= ? " +
                     "AND (fecha_fin IS NULL OR fecha_fin >= ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, idUsuario);
            statement.setDate(2, Date.valueOf(fecha));
            statement.setDate(3, Date.valueOf(fecha));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando contrato vigente en fecha específica: " + exception.getMessage(),
                    "Error al verificar si existe un contrato vigente para la fecha de evaluación",
                    exception
            );
        }

        return false;
    }

    @Override
    public List<ContratoEntity> findByUsuario(UUID idUsuario) {
        var filter = ContratoEntity.create();
        filter = ContratoEntity.create(
                UUIDHelper.getDefaultUUID(),
                idUsuario,
                null,
                null,
                null
        );
        return read(filter);
    }
}