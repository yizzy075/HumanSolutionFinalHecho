package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;

import java.util.List;
import java.util.UUID;

public interface EvaluacionDesempenoFacade {

    void create(EvaluacionDesempenoDTO dto);

    List<EvaluacionDesempenoDTO> list();

    List<EvaluacionDesempenoDTO> findByUsuario(UUID idUsuario);

    EvaluacionDesempenoDTO findById(UUID id);

    void update(EvaluacionDesempenoDTO dto);

    void delete(UUID id);
}