package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.TipoDocumentoDomain;

import java.util.List;
import java.util.UUID;

public interface TipoDocumentoBusiness {

    void create(TipoDocumentoDomain domain);

    List<TipoDocumentoDomain> list();

    TipoDocumentoDomain findById(UUID id);

    void update(TipoDocumentoDomain domain);

    void delete(UUID id);
}