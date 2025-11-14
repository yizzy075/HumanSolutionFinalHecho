package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.TipoDocumentoEntity;
import java.util.List;
import java.util.UUID;

public interface TipoDocumentoDAO {

    void create(TipoDocumentoEntity entity);

    List<TipoDocumentoEntity> read(TipoDocumentoEntity entity);

    void update(TipoDocumentoEntity entity);

    void delete(UUID id);

    boolean existsByNombre(String nombre);
}