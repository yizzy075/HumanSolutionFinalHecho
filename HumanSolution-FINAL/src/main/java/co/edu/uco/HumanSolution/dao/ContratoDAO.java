package co.edu.uco.HumanSolution.dao;

import co.edu.uco.HumanSolution.domain.Contrato;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * DAO para la gestión de contratos.
 * Utiliza DataSource inyectado para evitar problemas de conexiones cerradas.
 */
@Repository
public class ContratoDAO extends BaseDAO {

    /**
     * Constructor que recibe el DataSource por inyección de dependencias.
     * @param dataSource el DataSource configurado por Spring
     */
    public ContratoDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Verifica si existe un contrato con el ID especificado.
     *
     * @param id el UUID del contrato
     * @return true si existe, false en caso contrario
     */
    public boolean exists(UUID id) {
        String sql = "SELECT COUNT(*) FROM contrato WHERE id = ?";

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
            throw new RuntimeException("Error al verificar existencia de contrato: " + e.getMessage(), e);
        }
    }

    /**
     * Verifica si un contrato está vigente (la fecha actual está entre fecha_inicio y fecha_fin).
     *
     * @param id el UUID del contrato
     * @return true si está vigente, false en caso contrario
     */
    public boolean isVigente(UUID id) {
        String sql = "SELECT COUNT(*) FROM contrato WHERE id = ? AND vigente = true " +
                     "AND fecha_inicio <= CURRENT_DATE AND (fecha_fin IS NULL OR fecha_fin >= CURRENT_DATE)";

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
            throw new RuntimeException("Error al verificar vigencia de contrato: " + e.getMessage(), e);
        }
    }

    /**
     * Crea un nuevo contrato.
     *
     * @param contrato el contrato a crear
     * @return el contrato creado con su ID
     */
    public Contrato create(Contrato contrato) {
        String sql = "INSERT INTO contrato (id, id_usuario, fecha_inicio, fecha_fin, tipo, vigente) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (contrato.getId() == null) {
                contrato.setId(UUID.randomUUID());
            }

            stmt.setObject(1, contrato.getId());
            stmt.setObject(2, contrato.getIdUsuario());
            stmt.setDate(3, Date.valueOf(contrato.getFechaInicio()));
            stmt.setDate(4, contrato.getFechaFin() != null ? Date.valueOf(contrato.getFechaFin()) : null);
            stmt.setString(5, contrato.getTipo());
            stmt.setBoolean(6, contrato.isVigente());

            stmt.executeUpdate();

            return contrato;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear contrato: " + e.getMessage(), e);
        }
    }

    /**
     * Busca un contrato por su ID.
     *
     * @param id el UUID del contrato
     * @return un Optional con el contrato si existe
     */
    public Optional<Contrato> findById(UUID id) {
        String sql = "SELECT * FROM contrato WHERE id = ?";

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
            throw new RuntimeException("Error al buscar contrato por ID: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene todos los contratos de un usuario.
     *
     * @param idUsuario el UUID del usuario
     * @return lista de contratos del usuario
     */
    public List<Contrato> findByUsuario(UUID idUsuario) {
        String sql = "SELECT * FROM contrato WHERE id_usuario = ? ORDER BY fecha_inicio DESC";
        List<Contrato> contratos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contratos.add(mapResultSetToEntity(rs));
                }
            }

            return contratos;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar contratos por usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene todos los contratos vigentes de un usuario.
     *
     * @param idUsuario el UUID del usuario
     * @return lista de contratos vigentes del usuario
     */
    public List<Contrato> findVigentesByUsuario(UUID idUsuario) {
        String sql = "SELECT * FROM contrato WHERE id_usuario = ? AND vigente = true " +
                     "AND fecha_inicio <= CURRENT_DATE AND (fecha_fin IS NULL OR fecha_fin >= CURRENT_DATE) " +
                     "ORDER BY fecha_inicio DESC";
        List<Contrato> contratos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contratos.add(mapResultSetToEntity(rs));
                }
            }

            return contratos;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar contratos vigentes por usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene todos los contratos.
     *
     * @return lista de todos los contratos
     */
    public List<Contrato> findAll() {
        String sql = "SELECT * FROM contrato ORDER BY fecha_inicio DESC";
        List<Contrato> contratos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contratos.add(mapResultSetToEntity(rs));
                }
            }

            return contratos;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los contratos: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza un contrato existente.
     *
     * @param contrato el contrato con los datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean update(Contrato contrato) {
        String sql = "UPDATE contrato SET id_usuario = ?, fecha_inicio = ?, fecha_fin = ?, " +
                     "tipo = ?, vigente = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, contrato.getIdUsuario());
            stmt.setDate(2, Date.valueOf(contrato.getFechaInicio()));
            stmt.setDate(3, contrato.getFechaFin() != null ? Date.valueOf(contrato.getFechaFin()) : null);
            stmt.setString(4, contrato.getTipo());
            stmt.setBoolean(5, contrato.isVigente());
            stmt.setObject(6, contrato.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar contrato: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un contrato por su ID.
     *
     * @param id el UUID del contrato
     * @return true si se eliminó correctamente
     */
    public boolean delete(UUID id) {
        String sql = "DELETE FROM contrato WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar contrato: " + e.getMessage(), e);
        }
    }

    /**
     * Mapea un ResultSet a una entidad Contrato.
     *
     * @param rs el ResultSet
     * @return la entidad mapeada
     * @throws SQLException si hay error al leer el ResultSet
     */
    private Contrato mapResultSetToEntity(ResultSet rs) throws SQLException {
        Contrato contrato = new Contrato();
        contrato.setId((UUID) rs.getObject("id"));
        contrato.setIdUsuario((UUID) rs.getObject("id_usuario"));
        contrato.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());

        Date fechaFin = rs.getDate("fecha_fin");
        if (fechaFin != null) {
            contrato.setFechaFin(fechaFin.toLocalDate());
        }

        contrato.setTipo(rs.getString("tipo"));
        contrato.setVigente(rs.getBoolean("vigente"));
        return contrato;
    }
}
