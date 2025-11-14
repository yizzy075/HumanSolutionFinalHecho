package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.TipoHoraExtraDomain;

import java.util.List;
import java.util.UUID;

public interface TipoHoraExtraBusiness {

    void create(TipoHoraExtraDomain domain);

    List<TipoHoraExtraDomain> list();

    TipoHoraExtraDomain findById(UUID id);

    void update(TipoHoraExtraDomain domain);

    void delete(UUID id);
}