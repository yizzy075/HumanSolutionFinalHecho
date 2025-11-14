package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.UsuarioDocumentoEntity;
import java.util.List;
import java.util.UUID;

public interface UsuarioDocumentoDAO {

    void create(UsuarioDocumentoEntity entity);

    List<UsuarioDocumentoEntity> read(UsuarioDocumentoEntity entity);

    void update(UsuarioDocumentoEntity entity);

    void delete(UUID id);
}