package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.TipoPermisoDomain;

import java.util.List;
import java.util.UUID;

public interface TipoPermisoBusiness {

    void create(TipoPermisoDomain domain);

    List<TipoPermisoDomain> list();

    TipoPermisoDomain findById(UUID id);

    void update(TipoPermisoDomain domain);

    void delete(UUID id);
}