package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.ExperienciaLaboralDomain;
import co.edu.uco.HumanSolution.entity.ExperienciaLaboralEntity;

import java.util.ArrayList;
import java.util.List;

public final class ExperienciaLaboralEntityAssembler {

    private static final ExperienciaLaboralEntityAssembler instance = new ExperienciaLaboralEntityAssembler();

    private ExperienciaLaboralEntityAssembler() {
    }

    public static ExperienciaLaboralEntityAssembler getExperienciaLaboralEntityAssembler() {
        return instance;
    }

    public ExperienciaLaboralDomain toDomain(ExperienciaLaboralEntity entity) {
        return ExperienciaLaboralDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getEmpresa(),
                entity.getCargo(),
                entity.getFechaInicio(),
                entity.getFechaFin()
        );
    }

    public ExperienciaLaboralEntity toEntity(ExperienciaLaboralDomain domain) {
        return ExperienciaLaboralEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getEmpresa(),
                domain.getCargo(),
                domain.getFechaInicio(),
                domain.getFechaFin()
        );
    }

    public List<ExperienciaLaboralDomain> toDomainList(List<ExperienciaLaboralEntity> entities) {
        List<ExperienciaLaboralDomain> domains = new ArrayList<>();
        for (ExperienciaLaboralEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}