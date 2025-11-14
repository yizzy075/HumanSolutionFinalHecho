package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.RolPermisoDomain;

import java.util.List;
import java.util.UUID;

public interface RolPermisoBusiness {

    void create(RolPermisoDomain domain);

    List<RolPermisoDomain> list();

    List<RolPermisoDomain> findByRol(UUID idRol);

    void delete(UUID id);
}