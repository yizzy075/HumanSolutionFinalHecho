package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.EstadoPuestoDomain;

import java.util.List;
import java.util.UUID;

public interface EstadoPuestoBusiness {

    void create(EstadoPuestoDomain domain);

    List<EstadoPuestoDomain> list();

    EstadoPuestoDomain findById(UUID id);

    void update(EstadoPuestoDomain domain);

    void delete(UUID id);
}