package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.UnidadOrganizativaDomain;

import java.util.List;
import java.util.UUID;

public interface UnidadOrganizativaBusiness {

    void create(UnidadOrganizativaDomain domain);

    List<UnidadOrganizativaDomain> list();

    UnidadOrganizativaDomain findById(UUID id);

    void update(UnidadOrganizativaDomain domain);

    void delete(UUID id);
}