package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.EstadoPuestoDTO;

import java.util.List;
import java.util.UUID;

public interface EstadoPuestoFacade {

    void create(EstadoPuestoDTO dto);

    List<EstadoPuestoDTO> list();

    EstadoPuestoDTO findById(UUID id);

    void update(EstadoPuestoDTO dto);

    void delete(UUID id);
}