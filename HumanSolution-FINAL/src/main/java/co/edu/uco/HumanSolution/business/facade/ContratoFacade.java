package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.ContratoDTO;

import java.util.List;
import java.util.UUID;

public interface ContratoFacade {

    void create(ContratoDTO dto);

    List<ContratoDTO> list();

    List<ContratoDTO> findByUsuario(UUID idUsuario);

    ContratoDTO findById(UUID id);

    void update(ContratoDTO dto);

    void delete(UUID id);
}
