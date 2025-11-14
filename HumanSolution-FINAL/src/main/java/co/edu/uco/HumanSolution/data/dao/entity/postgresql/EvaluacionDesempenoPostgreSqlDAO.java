package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.dao.EvaluacionDesempenoDAO;
import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EvaluacionDesempenoPostgreSqlDAO implements EvaluacionDesempenoDAO {

    private Connection connection;

    public EvaluacionDesempenoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EvaluacionDesempenoEntity entity) {
        String sql = "INSERT INTO evaluacion_desempeno (id, id_usuario, id_evaluador, id_contrato, fecha, calificacion, observacion, criterios) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        System.out.println("======= DEBUG CREATE EVALUACION =======");
        System.out.println("Entity: " + entity.getId());
        System.out.println("ID Usuario: " + entity.getIdUsuario());
        System.out.println("ID Evaluador: " + entity.getIdEvaluador());
        System.out.println("ID Contrato: " + entity.getIdContrato());

        try (PreparedStatement statement = connection.prepareStatement(sql)) {  // ✅ try-with-resources
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setObject(3, entity.getIdEvaluador());
            statement.setObject(4, entity.getIdContrato());
            statement.setDate(5, java.sql.Date.valueOf(entity.getFecha()));
            statement.setInt(6, entity.getCalificacion());
            statement.setString(7, entity.getObservacion());
            statement.setString(8, entity.getCriterios());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new HumanSolutionException(
                    "DAO-CREATE: Error al crear evaluación de desempeño: " + e.getMessage(),
                    "Error al registrar la evaluación de desempeño",
                    e
            );
        }
    }

    @Override
    public List<EvaluacionDesempenoEntity> read(EvaluacionDesempenoEntity entity) {
        List<EvaluacionDesempenoEntity> evaluaciones = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder("SELECT id, id_usuario, id_evaluador, id_contrato, fecha, calificacion, observacion, criterios FROM evaluacion_desempeno WHERE 1=1");

            if (entity.getId() != null) {
                sql.append(" AND id = ?");
            }
            if (entity.getIdUsuario() != null) {
                sql.append(" AND id_usuario = ?");
            }
            if (entity.getFecha() != null) {
                sql.append(" AND fecha = ?");
            }

            try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {  // ✅ try-with-resources
                int paramIndex = 1;
                if (entity.getId() != null) {
                    statement.setObject(paramIndex++, entity.getId());
                }
                if (entity.getIdUsuario() != null) {
                    statement.setObject(paramIndex++, entity.getIdUsuario());
                }
                if (entity.getFecha() != null) {
                    statement.setDate(paramIndex++, java.sql.Date.valueOf(entity.getFecha()));
                }

                try (ResultSet resultSet = statement.executeQuery()) {  // ✅ try-with-resources
                    while (resultSet.next()) {
                        EvaluacionDesempenoEntity evaluacion = EvaluacionDesempenoEntity.create(
                                UUID.fromString(resultSet.getString("id")),
                                UUID.fromString(resultSet.getString("id_usuario")),
                                UUID.fromString(resultSet.getString("id_evaluador")),
                                UUID.fromString(resultSet.getString("id_contrato")),
                                resultSet.getDate("fecha").toLocalDate(),
                                resultSet.getInt("calificacion"),
                                resultSet.getString("observacion"),
                                resultSet.getString("criterios")
                        );
                        evaluaciones.add(evaluacion);
                    }
                }
            }

        } catch (SQLException e) {
            throw new HumanSolutionException(
                    "DAO-READ: Error al leer evaluaciones de desempeño: " + e.getMessage(),
                    "Error al consultar las evaluaciones de desempeño",
                    e
            );
        }

        return evaluaciones;
    }

    @Override
    public void update(EvaluacionDesempenoEntity entity) {
        String sql = "UPDATE evaluacion_desempeno SET id_usuario = ?, id_evaluador = ?, id_contrato = ?, " +
                "fecha = ?, calificacion = ?, observacion = ?, criterios = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {  // ✅ try-with-resources
            statement.setObject(1, entity.getIdUsuario());
            statement.setObject(2, entity.getIdEvaluador());
            statement.setObject(3, entity.getIdContrato());
            statement.setDate(4, java.sql.Date.valueOf(entity.getFecha()));
            statement.setInt(5, entity.getCalificacion());
            statement.setString(6, entity.getObservacion());
            statement.setString(7, entity.getCriterios());
            statement.setObject(8, entity.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new HumanSolutionException(
                    "DAO-UPDATE: Error al actualizar evaluación de desempeño: " + e.getMessage(),
                    "Error al actualizar la evaluación de desempeño",
                    e
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM evaluacion_desempeno WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {  // ✅ try-with-resources
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new HumanSolutionException(
                    "DAO-DELETE: Error al eliminar evaluación de desempeño: " + e.getMessage(),
                    "Error al eliminar la evaluación de desempeño",
                    e
            );
        }
    }

    @Override
    public boolean existsByUsuarioIdAndFecha(UUID idUsuario, LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM evaluacion_desempeno WHERE id_usuario = ? AND fecha = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, idUsuario);
            statement.setDate(2, java.sql.Date.valueOf(fecha));

            try (ResultSet resultSet = statement.executeQuery()) {  // ✅ try-with-resources
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

            return false;

        } catch (SQLException e) {
            throw new HumanSolutionException(
                    "DAO-EXISTS: Error al verificar existencia de evaluación: " + e.getMessage(),
                    "Error al verificar la existencia de la evaluación",
                    e
            );
        }
    }

    @Override
    public Optional<EvaluacionDesempenoEntity> findByUsuarioIdAndFecha(UUID idUsuario, LocalDate fecha) {
        String sql = "SELECT id, id_usuario, id_evaluador, id_contrato, fecha, calificacion, observacion, criterios " +
                "FROM evaluacion_desempeno WHERE id_usuario = ? AND fecha = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {  // ✅ try-with-resources
            statement.setObject(1, idUsuario);
            statement.setDate(2, java.sql.Date.valueOf(fecha));

            try (ResultSet resultSet = statement.executeQuery()) {  // ✅ try-with-resources
                if (resultSet.next()) {
                    EvaluacionDesempenoEntity entity = EvaluacionDesempenoEntity.create(
                            UUID.fromString(resultSet.getString("id")),
                            UUID.fromString(resultSet.getString("id_usuario")),
                            UUID.fromString(resultSet.getString("id_evaluador")),
                            UUID.fromString(resultSet.getString("id_contrato")),
                            resultSet.getDate("fecha").toLocalDate(),
                            resultSet.getInt("calificacion"),
                            resultSet.getString("observacion"),
                            resultSet.getString("criterios")
                    );

                    return Optional.of(entity);
                }
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new HumanSolutionException(
                    "DAO-FIND: Error al buscar evaluación por usuario y fecha: " + e.getMessage(),
                    "Error al buscar la evaluación",
                    e
            );
        }
    }

    @Override
    public List<EvaluacionDesempenoEntity> findByUsuario(UUID idUsuario) {
        List<EvaluacionDesempenoEntity> evaluaciones = new ArrayList<>();
        String sql = "SELECT id, id_usuario, id_evaluador, id_contrato, fecha, calificacion, observacion, criterios " +
                "FROM evaluacion_desempeno WHERE id_usuario = ? ORDER BY fecha DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {  // ✅ try-with-resources
            statement.setObject(1, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {  // ✅ try-with-resources
                while (resultSet.next()) {
                    EvaluacionDesempenoEntity evaluacion = EvaluacionDesempenoEntity.create(
                            UUID.fromString(resultSet.getString("id")),
                            UUID.fromString(resultSet.getString("id_usuario")),
                            UUID.fromString(resultSet.getString("id_evaluador")),
                            UUID.fromString(resultSet.getString("id_contrato")),
                            resultSet.getDate("fecha").toLocalDate(),
                            resultSet.getInt("calificacion"),
                            resultSet.getString("observacion"),
                            resultSet.getString("criterios")
                    );
                    evaluaciones.add(evaluacion);
                }
            }

        } catch (SQLException e) {
            throw new HumanSolutionException(
                    "DAO-FIND-BY-USER: Error al buscar evaluaciones por usuario: " + e.getMessage(),
                    "Error al buscar las evaluaciones del usuario",
                    e
            );
        }

        return evaluaciones;
    }
}