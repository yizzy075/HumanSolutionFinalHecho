package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.PermisoSistemaDomain;

import java.util.List;
import java.util.UUID;

public interface PermisoSistemaBusiness {

    void create(PermisoSistemaDomain domain);

    List<PermisoSistemaDomain> list();

    PermisoSistemaDomain findById(UUID id);

    void update(PermisoSistemaDomain domain);

    void delete(UUID id);
}