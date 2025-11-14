package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.PermisoSistemaDTO;

import java.util.List;
import java.util.UUID;

public interface PermisoSistemaFacade {

    void create(PermisoSistemaDTO dto);

    List<PermisoSistemaDTO> list();

    PermisoSistemaDTO findById(UUID id);

    void update(PermisoSistemaDTO dto);

    void delete(UUID id);
}