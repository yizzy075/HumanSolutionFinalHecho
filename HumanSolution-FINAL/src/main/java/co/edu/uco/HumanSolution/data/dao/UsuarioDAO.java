package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.UsuarioEntity;

import java.util.List;
import java.util.UUID;

public interface UsuarioDAO {

    void create(UsuarioEntity entity);

    List<UsuarioEntity> read(UsuarioEntity filter);

    void update(UsuarioEntity entity);

    void delete(UUID id);

    boolean existsByEmail(String email);

    boolean existsByDocumento(String documento);
}