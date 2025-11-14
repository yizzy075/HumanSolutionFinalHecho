package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.TipoHoraExtraDTO;

import java.util.List;
import java.util.UUID;

public interface TipoHoraExtraFacade {

    void create(TipoHoraExtraDTO dto);

    List<TipoHoraExtraDTO> list();

    TipoHoraExtraDTO findById(UUID id);

    void update(TipoHoraExtraDTO dto);

    void delete(UUID id);
}