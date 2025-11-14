package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.PuestoDomain;
import co.edu.uco.HumanSolution.entity.PuestoEntity;

import java.util.ArrayList;
import java.util.List;

public final class PuestoEntityAssembler {

    private static final PuestoEntityAssembler instance = new PuestoEntityAssembler();

    private PuestoEntityAssembler() {
    }

    public static PuestoEntityAssembler getPuestoEntityAssembler() {
        return instance;
    }

    public PuestoDomain toDomain(PuestoEntity entity) {
        return PuestoDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getIdUsuario(),
                entity.getIdUnidad(),
                entity.getIdEstado(),
                entity.getIdJefe()
        );
    }

    public PuestoEntity toEntity(PuestoDomain domain) {
        return PuestoEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getIdUsuario(),
                domain.getIdUnidad(),
                domain.getIdEstado(),
                domain.getIdJefe()
        );
    }

    public List<PuestoDomain> toDomainList(List<PuestoEntity> entities) {
        List<PuestoDomain> domains = new ArrayList<>();
        for (PuestoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}