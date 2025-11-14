package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.EstadoPuestoEntity;
import java.util.List;
import java.util.UUID;

public interface EstadoPuestoDAO {

    void create(EstadoPuestoEntity entity);

    List<EstadoPuestoEntity> read(EstadoPuestoEntity entity);

    void update(EstadoPuestoEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}