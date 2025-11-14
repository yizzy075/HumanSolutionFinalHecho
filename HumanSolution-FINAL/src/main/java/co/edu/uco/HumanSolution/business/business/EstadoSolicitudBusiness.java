package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.EstadoSolicitudDomain;

import java.util.List;
import java.util.UUID;

public interface EstadoSolicitudBusiness {

    void create(EstadoSolicitudDomain domain);

    List<EstadoSolicitudDomain> list();

    EstadoSolicitudDomain findById(UUID id);

    void update(EstadoSolicitudDomain domain);

    void delete(UUID id);
}