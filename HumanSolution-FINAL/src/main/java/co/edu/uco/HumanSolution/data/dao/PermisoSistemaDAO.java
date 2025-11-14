package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.PermisoSistemaEntity;
import java.util.List;
import java.util.UUID;

public interface PermisoSistemaDAO {

    void create(PermisoSistemaEntity entity);

    List<PermisoSistemaEntity> read(PermisoSistemaEntity entity);

    void update(PermisoSistemaEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}