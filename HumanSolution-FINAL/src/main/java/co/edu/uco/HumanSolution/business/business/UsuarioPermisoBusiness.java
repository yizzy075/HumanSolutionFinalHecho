package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.UsuarioPermisoDomain;

import java.util.List;
import java.util.UUID;

public interface UsuarioPermisoBusiness {

    void create(UsuarioPermisoDomain domain);

    List<UsuarioPermisoDomain> list();

    List<UsuarioPermisoDomain> findByUsuario(UUID idUsuario);

    UsuarioPermisoDomain findById(UUID id);

    void update(UsuarioPermisoDomain domain);

    void delete(UUID id);
}