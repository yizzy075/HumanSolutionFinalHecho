package co.edu.uco.HumanSolution.dao;

import co.edu.uco.HumanSolution.domain.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * DAO para la gestión de usuarios.
 * Utiliza DataSource inyectado para evitar problemas de conexiones cerradas.
 */
@Repository
public class UsuarioDAO extends BaseDAO {

    /**
     * Constructor que recibe el DataSource por inyección de dependencias.
     * @param dataSource el DataSource configurado por Spring
     */
    public UsuarioDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Verifica si existe un usuario con el ID especificado.
     *
     * @param id el UUID del usuario
     * @return true si existe, false en caso contrario
     */
    public boolean exists(UUID id) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE id = ?";

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
            throw new RuntimeException("Error al verificar existencia de usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Verifica si existe un usuario con el email especificado.
     *
     * @param email el email del usuario
     * @return true si existe, false en caso contrario
     */
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de usuario por email: " + e.getMessage(), e);
        }
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario el usuario a crear
     * @return el usuario creado con su ID
     */
    public Usuario create(Usuario usuario) {
        String sql = "INSERT INTO usuario (id, nombre, email, cargo) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (usuario.getId() == null) {
                usuario.setId(UUID.randomUUID());
            }

            stmt.setObject(1, usuario.getId());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCargo());

            stmt.executeUpdate();

            return usuario;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id el UUID del usuario
     * @return un Optional con el usuario si existe
     */
    public Optional<Usuario> findById(UUID id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

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
            throw new RuntimeException("Error al buscar usuario por ID: " + e.getMessage(), e);
        }
    }

    /**
     * Busca un usuario por su email.
     *
     * @param email el email del usuario
     * @return un Optional con el usuario si existe
     */
    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEntity(rs));
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por email: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return lista de todos los usuarios
     */
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuario ORDER BY nombre";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultSetToEntity(rs));
                }
            }

            return usuarios;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los usuarios: " + e.getMessage(), e);
        }
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param usuario el usuario con los datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, email = ?, cargo = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCargo());
            stmt.setObject(4, usuario.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id el UUID del usuario
     * @return true si se eliminó correctamente
     */
    public boolean delete(UUID id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Mapea un ResultSet a una entidad Usuario.
     *
     * @param rs el ResultSet
     * @return la entidad mapeada
     * @throws SQLException si hay error al leer el ResultSet
     */
    private Usuario mapResultSetToEntity(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId((UUID) rs.getObject("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setEmail(rs.getString("email"));
        usuario.setCargo(rs.getString("cargo"));
        return usuario;
    }
}
