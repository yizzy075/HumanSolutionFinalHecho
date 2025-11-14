package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.EstadoSolicitudDTO;

import java.util.List;
import java.util.UUID;

public interface EstadoSolicitudFacade {

    void create(EstadoSolicitudDTO dto);

    List<EstadoSolicitudDTO> list();

    EstadoSolicitudDTO findById(UUID id);

    void update(EstadoSolicitudDTO dto);

    void delete(UUID id);
}