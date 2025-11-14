package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;

import java.util.List;
import java.util.UUID;

public interface EvaluacionDesempenoBusiness {

    void create(EvaluacionDesempenoDomain domain);

    List<EvaluacionDesempenoDomain> list();

    List<EvaluacionDesempenoDomain> findByUsuario(UUID idUsuario);

    EvaluacionDesempenoDomain findById(UUID id);

    void update(EvaluacionDesempenoDomain domain);

    void delete(UUID id);
}