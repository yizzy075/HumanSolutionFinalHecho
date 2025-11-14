package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.TipoPermisoEntity;
import java.util.List;
import java.util.UUID;

public interface TipoPermisoDAO {

    void create(TipoPermisoEntity entity);

    List<TipoPermisoEntity> read(TipoPermisoEntity entity);

    void update(TipoPermisoEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}