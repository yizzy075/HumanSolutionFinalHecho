package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.ContratoDomain;

import java.util.List;
import java.util.UUID;

public interface ContratoBusiness {

    void create(ContratoDomain domain);

    List<ContratoDomain> list();

    List<ContratoDomain> findByUsuario(UUID idUsuario);

    ContratoDomain findById(UUID id);

    void update(ContratoDomain domain);

    void delete(UUID id);
}