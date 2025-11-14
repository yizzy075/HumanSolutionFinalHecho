package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.PuestoDTO;

import java.util.List;
import java.util.UUID;

public interface PuestoFacade {

    void create(PuestoDTO dto);

    List<PuestoDTO> list();

    List<PuestoDTO> findByUnidad(UUID idUnidad);

    PuestoDTO findById(UUID id);

    void update(PuestoDTO dto);

    void delete(UUID id);
}