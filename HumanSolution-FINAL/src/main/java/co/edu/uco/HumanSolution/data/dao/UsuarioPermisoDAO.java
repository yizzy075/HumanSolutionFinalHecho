package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.UsuarioPermisoEntity;
import java.util.List;
import java.util.UUID;

public interface UsuarioPermisoDAO {

    void create(UsuarioPermisoEntity entity);

    List<UsuarioPermisoEntity> read(UsuarioPermisoEntity entity);

    void update(UsuarioPermisoEntity entity);

    void delete(UUID id);
}