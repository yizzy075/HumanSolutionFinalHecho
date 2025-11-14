package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.RolDTO;

import java.util.List;
import java.util.UUID;

public interface RolFacade {

    void create(RolDTO dto);

    List<RolDTO> list();

    RolDTO findById(UUID id);

    void update(RolDTO dto);

    void delete(UUID id);
}