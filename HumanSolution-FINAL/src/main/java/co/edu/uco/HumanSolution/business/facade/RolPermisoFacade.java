package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.RolPermisoDTO;

import java.util.List;
import java.util.UUID;

public interface RolPermisoFacade {

    void create(RolPermisoDTO dto);

    List<RolPermisoDTO> list();

    List<RolPermisoDTO> findByRol(UUID idRol);

    void delete(UUID id);
}