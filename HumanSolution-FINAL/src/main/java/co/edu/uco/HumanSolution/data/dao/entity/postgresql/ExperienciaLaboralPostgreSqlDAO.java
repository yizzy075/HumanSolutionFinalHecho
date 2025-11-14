package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.ExperienciaLaboralDAO;
import co.edu.uco.HumanSolution.entity.ExperienciaLaboralEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExperienciaLaboralPostgreSqlDAO implements ExperienciaLaboralDAO {

    private Connection connection;

    public ExperienciaLaboralPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ExperienciaLaboralEntity entity) {
        String sql = "INSERT INTO experiencia_laboral (id, id_usuario, empresa, cargo, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setString(3, entity.getEmpresa());
            statement.setString(4, entity.getCargo());
            statement.setDate(5, Date.valueOf(entity.getFechaInicio()));

            if (entity.getFechaFin() != null) {
                statement.setDate(6, Date.valueOf(entity.getFechaFin()));
            } else {
                statement.setDate(6, null);
            }

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando experiencia laboral",
                    "Error al crear experiencia laboral en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<ExperienciaLaboralEntity> read(ExperienciaLaboralEntity entity) {
        List<ExperienciaLaboralEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, id_usuario, empresa, cargo, fecha_inicio, fecha_fin FROM experiencia_laboral WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        if (!entity.getEmpresa().isEmpty()) {
            sql.append(" AND empresa ILIKE ?");
            parametros.add("%" + entity.getEmpresa() + "%");
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Date fechaFin = resultSet.getDate("fecha_fin");

                    ExperienciaLaboralEntity resultado = ExperienciaLaboralEntity.create(
                            (UUID) resultSet.getObject("id"),
                            (UUID) resultSet.getObject("id_usuario"),
                            resultSet.getString("empresa"),
                            resultSet.getString("cargo"),
                            resultSet.getDate("fecha_inicio").toLocalDate(),
                            fechaFin != null ? fechaFin.toLocalDate() : null
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando experiencias laborales",
                    "Error al consultar experiencias laborales",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(ExperienciaLaboralEntity entity) {
        String sql = "UPDATE experiencia_laboral SET id_usuario = ?, empresa = ?, cargo = ?, fecha_inicio = ?, fecha_fin = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdUsuario());
            statement.setString(2, entity.getEmpresa());
            statement.setString(3, entity.getCargo());
            statement.setDate(4, Date.valueOf(entity.getFechaInicio()));

            if (entity.getFechaFin() != null) {
                statement.setDate(5, Date.valueOf(entity.getFechaFin()));
            } else {
                statement.setDate(5, null);
            }

            statement.setObject(6, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando experiencia laboral",
                    "Error al actualizar experiencia laboral",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM experiencia_laboral WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando experiencia laboral",
                    "Error al eliminar experiencia laboral",
                    exception
            );
        }
    }
}