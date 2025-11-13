package co.edu.uco.HumanSolution.dao;

import co.edu.uco.HumanSolution.domain.EvaluacionDesempeno;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * DAO para la gestión de evaluaciones de desempeño.
 * Utiliza DataSource inyectado para evitar problemas de conexiones cerradas.
 */
@Repository
public class EvaluacionDesempenoDAO extends BaseDAO {

    /**
     * Constructor que recibe el DataSource por inyección de dependencias.
     * @param dataSource el DataSource configurado por Spring
     */
    public EvaluacionDesempenoDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Verifica si existe una evaluación con el ID especificado.
     * Este método resuelve el error "DAO-EXISTS: Error al verificar existencia de evaluación".
     *
     * @param id el UUID de la evaluación
     * @return true si existe, false en caso contrario
     */
    public boolean exists(UUID id) {
        String sql = "SELECT COUNT(*) FROM evaluacion_desempeno WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("DAO-EXISTS: Error al verificar existencia de evaluación: " + e.getMessage(), e);
        }
    }

    /**
     * Verifica si existe una evaluación para un usuario en una fecha específica.
     * Regla de negocio ED-02: No se pueden registrar dos evaluaciones en la misma fecha para el mismo usuario.
     *
     * @param idUsuario el UUID del usuario
     * @param fecha la fecha de la evaluación
     * @return true si existe, false en caso contrario
     */
    public boolean existsByUsuarioAndFecha(UUID idUsuario, LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM evaluacion_desempeno WHERE id_usuario = ? AND fecha = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, idUsuario);
            stmt.setDate(2, Date.valueOf(fecha));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de evaluación por usuario y fecha: " + e.getMessage(), e);
        }
    }

    /**
     * Crea una nueva evaluación de desempeño.
     *
     * @param evaluacion la evaluación a crear
     * @return la evaluación creada con su ID
     */
    public EvaluacionDesempeno create(EvaluacionDesempeno evaluacion) {
        String sql = "INSERT INTO evaluacion_desempeno (id, id_usuario, id_evaluador, id_contrato, " +
                     "fecha, calificacion, observacion, criterios) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (evaluacion.getId() == null) {
                evaluacion.setId(UUID.randomUUID());
            }

            stmt.setObject(1, evaluacion.getId());
            stmt.setObject(2, evaluacion.getIdUsuario());
            stmt.setObject(3, evaluacion.getIdEvaluador());
            stmt.setObject(4, evaluacion.getIdContrato());
            stmt.setDate(5, Date.valueOf(evaluacion.getFecha()));
            stmt.setInt(6, evaluacion.getCalificacion());
            stmt.setString(7, evaluacion.getObservacion());
            stmt.setString(8, evaluacion.getCriterios());

            stmt.executeUpdate();

            return evaluacion;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear evaluación de desempeño: " + e.getMessage(), e);
        }
    }

    /**
     * Busca una evaluación por su ID.
     *
     * @param id el UUID de la evaluación
     * @return un Optional con la evaluación si existe
     */
    public Optional<EvaluacionDesempeno> findById(UUID id) {
        String sql = "SELECT * FROM evaluacion_desempeno WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEntity(rs));
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar evaluación por ID: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene todas las evaluaciones de un usuario.
     *
     * @param idUsuario el UUID del usuario
     * @return lista de evaluaciones del usuario
     */
    public List<EvaluacionDesempeno> findByUsuario(UUID idUsuario) {
        String sql = "SELECT * FROM evaluacion_desempeno WHERE id_usuario = ? ORDER BY fecha DESC";
        List<EvaluacionDesempeno> evaluaciones = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    evaluaciones.add(mapResultSetToEntity(rs));
                }
            }

            return evaluaciones;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar evaluaciones por usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene todas las evaluaciones.
     *
     * @return lista de todas las evaluaciones
     */
    public List<EvaluacionDesempeno> findAll() {
        String sql = "SELECT * FROM evaluacion_desempeno ORDER BY fecha DESC";
        List<EvaluacionDesempeno> evaluaciones = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    evaluaciones.add(mapResultSetToEntity(rs));
                }
            }

            return evaluaciones;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todas las evaluaciones: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza una evaluación existente.
     *
     * @param evaluacion la evaluación con los datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean update(EvaluacionDesempeno evaluacion) {
        String sql = "UPDATE evaluacion_desempeno SET id_usuario = ?, id_evaluador = ?, " +
                     "id_contrato = ?, fecha = ?, calificacion = ?, observacion = ?, criterios = ? " +
                     "WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, evaluacion.getIdUsuario());
            stmt.setObject(2, evaluacion.getIdEvaluador());
            stmt.setObject(3, evaluacion.getIdContrato());
            stmt.setDate(4, Date.valueOf(evaluacion.getFecha()));
            stmt.setInt(5, evaluacion.getCalificacion());
            stmt.setString(6, evaluacion.getObservacion());
            stmt.setString(7, evaluacion.getCriterios());
            stmt.setObject(8, evaluacion.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar evaluación: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina una evaluación por su ID.
     *
     * @param id el UUID de la evaluación
     * @return true si se eliminó correctamente
     */
    public boolean delete(UUID id) {
        String sql = "DELETE FROM evaluacion_desempeno WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar evaluación: " + e.getMessage(), e);
        }
    }

    /**
     * Mapea un ResultSet a una entidad EvaluacionDesempeno.
     *
     * @param rs el ResultSet
     * @return la entidad mapeada
     * @throws SQLException si hay error al leer el ResultSet
     */
    private EvaluacionDesempeno mapResultSetToEntity(ResultSet rs) throws SQLException {
        EvaluacionDesempeno evaluacion = new EvaluacionDesempeno();
        evaluacion.setId((UUID) rs.getObject("id"));
        evaluacion.setIdUsuario((UUID) rs.getObject("id_usuario"));
        evaluacion.setIdEvaluador((UUID) rs.getObject("id_evaluador"));
        evaluacion.setIdContrato((UUID) rs.getObject("id_contrato"));
        evaluacion.setFecha(rs.getDate("fecha").toLocalDate());
        evaluacion.setCalificacion(rs.getInt("calificacion"));
        evaluacion.setObservacion(rs.getString("observacion"));
        evaluacion.setCriterios(rs.getString("criterios"));
        return evaluacion;
    }
}
