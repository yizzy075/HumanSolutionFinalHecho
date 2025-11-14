package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.RolDomain;

import java.util.List;
import java.util.UUID;

public interface RolBusiness {

    void create(RolDomain domain);

    List<RolDomain> list();

    RolDomain findById(UUID id);

    void update(RolDomain domain);

    void delete(UUID id);
}