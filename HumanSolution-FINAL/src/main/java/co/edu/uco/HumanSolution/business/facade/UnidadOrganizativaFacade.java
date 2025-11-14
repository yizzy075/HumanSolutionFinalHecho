package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.UnidadOrganizativaDTO;

import java.util.List;
import java.util.UUID;

public interface UnidadOrganizativaFacade {

    void create(UnidadOrganizativaDTO dto);

    List<UnidadOrganizativaDTO> list();

    UnidadOrganizativaDTO findById(UUID id);

    void update(UnidadOrganizativaDTO dto);

    void delete(UUID id);
}