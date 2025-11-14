package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.PuestoDomain;

import java.util.List;
import java.util.UUID;

public interface PuestoBusiness {

    void create(PuestoDomain domain);

    List<PuestoDomain> list();

    List<PuestoDomain> findByUnidad(UUID idUnidad);

    PuestoDomain findById(UUID id);

    void update(PuestoDomain domain);

    void delete(UUID id);
}