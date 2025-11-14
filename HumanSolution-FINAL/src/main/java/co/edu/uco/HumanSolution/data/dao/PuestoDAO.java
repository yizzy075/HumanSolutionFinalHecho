package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.PuestoEntity;
import java.util.List;
import java.util.UUID;

public interface PuestoDAO {

    void create(PuestoEntity entity);

    List<PuestoEntity> read(PuestoEntity entity);

    void update(PuestoEntity entity);

    void delete(UUID id);

    boolean existsByNombreAndUnidad(String nombre, UUID idUnidad);
}