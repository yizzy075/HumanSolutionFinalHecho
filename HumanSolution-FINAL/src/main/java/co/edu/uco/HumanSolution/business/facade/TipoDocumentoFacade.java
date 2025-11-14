package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.TipoDocumentoDTO;

import java.util.List;
import java.util.UUID;

public interface TipoDocumentoFacade {

    void create(TipoDocumentoDTO dto);

    List<TipoDocumentoDTO> list();

    TipoDocumentoDTO findById(UUID id);

    void update(TipoDocumentoDTO dto);

    void delete(UUID id);
}