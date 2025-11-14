package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.TipoPermisoDTO;

import java.util.List;
import java.util.UUID;

public interface TipoPermisoFacade {

    void create(TipoPermisoDTO dto);

    List<TipoPermisoDTO> list();

    TipoPermisoDTO findById(UUID id);

    void update(TipoPermisoDTO dto);

    void delete(UUID id);
}