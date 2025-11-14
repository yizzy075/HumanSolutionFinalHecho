package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.UnidadOrganizativaEntity;
import java.util.List;
import java.util.UUID;

public interface UnidadOrganizativaDAO {

    void create(UnidadOrganizativaEntity entity);

    List<UnidadOrganizativaEntity> read(UnidadOrganizativaEntity entity);

    void update(UnidadOrganizativaEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}