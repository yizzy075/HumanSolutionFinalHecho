package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.UsuarioHoraExtraEntity;
import java.util.List;
import java.util.UUID;

public interface UsuarioHoraExtraDAO {

    void create(UsuarioHoraExtraEntity entity);

    List<UsuarioHoraExtraEntity> read(UsuarioHoraExtraEntity entity);

    void update(UsuarioHoraExtraEntity entity);

    void delete(UUID id);
}