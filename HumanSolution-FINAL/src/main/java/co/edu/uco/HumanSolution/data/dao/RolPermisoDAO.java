package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.RolPermisoEntity;
import java.util.List;
import java.util.UUID;

public interface RolPermisoDAO {

    void create(RolPermisoEntity entity);

    List<RolPermisoEntity> read(RolPermisoEntity entity);

    void delete(UUID id);

    boolean existsByRolAndPermiso(UUID idRol, UUID idPermiso);
}