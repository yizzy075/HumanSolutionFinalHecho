package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.TipoHoraExtraEntity;
import java.util.List;
import java.util.UUID;

public interface TipoHoraExtraDAO {

    void create(TipoHoraExtraEntity entity);

    List<TipoHoraExtraEntity> read(TipoHoraExtraEntity entity);

    void update(TipoHoraExtraEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}