package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.ContratoDomain;
import co.edu.uco.HumanSolution.entity.ContratoEntity;

import java.util.ArrayList;
import java.util.List;

public final class ContratoEntityAssembler {

    private static final ContratoEntityAssembler instance = new ContratoEntityAssembler();

    private ContratoEntityAssembler() {
    }

    public static ContratoEntityAssembler getContratoEntityAssembler() {
        return instance;
    }

    public ContratoDomain toDomain(ContratoEntity entity) {
        return ContratoDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.getSueldo()
        );
    }

    public ContratoEntity toEntity(ContratoDomain domain) {
        return ContratoEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getSueldo()
        );
    }

    public List<ContratoDomain> toDomainList(List<ContratoEntity> entities) {
        List<ContratoDomain> domains = new ArrayList<>();
        for (ContratoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}