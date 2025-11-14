package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;

import java.util.List;
import java.util.UUID;

public interface UsuarioBusiness {

    void register(UsuarioDomain domain);

    List<UsuarioDomain> list();

    UsuarioDomain findById(UUID id);

    UsuarioDomain findByEmail(String email);

    void update(UsuarioDomain domain);

    void delete(UUID id);
}