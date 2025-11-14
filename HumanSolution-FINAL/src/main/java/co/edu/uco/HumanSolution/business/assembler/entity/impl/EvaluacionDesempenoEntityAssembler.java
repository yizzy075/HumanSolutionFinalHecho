package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;

import java.util.ArrayList;
import java.util.List;

public final class EvaluacionDesempenoEntityAssembler {

    private static final EvaluacionDesempenoEntityAssembler instance = new EvaluacionDesempenoEntityAssembler();

    private EvaluacionDesempenoEntityAssembler() {
    }

    public static EvaluacionDesempenoEntityAssembler getEvaluacionDesempenoEntityAssembler() {
        return instance;
    }

    public EvaluacionDesempenoDomain toDomain(EvaluacionDesempenoEntity entity) {
        return EvaluacionDesempenoDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getIdEvaluador(),
                entity.getIdContrato(),
                entity.getFecha(),
                entity.getCalificacion(),
                entity.getObservacion(),
                entity.getCriterios()
        );
    }

    public EvaluacionDesempenoEntity toEntity(EvaluacionDesempenoDomain domain) {
        return EvaluacionDesempenoEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getIdEvaluador(),
                domain.getIdContrato(),
                domain.getFecha(),
                domain.getCalificacion(),
                domain.getObservacion(),
                domain.getCriterios()
        );
    }

    public List<EvaluacionDesempenoDomain> toDomainList(List<EvaluacionDesempenoEntity> entities) {
        List<EvaluacionDesempenoDomain> domains = new ArrayList<>();
        for (EvaluacionDesempenoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}
