package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.EstadoSolicitudEntity;
import java.util.List;
import java.util.UUID;

public interface EstadoSolicitudDAO {

    void create(EstadoSolicitudEntity entity);

    List<EstadoSolicitudEntity> read(EstadoSolicitudEntity entity);

    void update(EstadoSolicitudEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}