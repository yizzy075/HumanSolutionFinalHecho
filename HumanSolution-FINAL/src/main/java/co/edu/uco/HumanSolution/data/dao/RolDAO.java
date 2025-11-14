package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.RolEntity;
import java.util.List;
import java.util.UUID;

public interface RolDAO {

    void create(RolEntity entity);

    List<RolEntity> read(RolEntity entity);

    void update(RolEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}