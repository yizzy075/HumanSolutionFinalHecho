package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.EstadoPuestoDomain;
import co.edu.uco.HumanSolution.entity.EstadoPuestoEntity;

import java.util.ArrayList;
import java.util.List;

public final class EstadoPuestoEntityAssembler {

    private static final EstadoPuestoEntityAssembler instance = new EstadoPuestoEntityAssembler();

    private EstadoPuestoEntityAssembler() {
    }

    public static EstadoPuestoEntityAssembler getEstadoPuestoEntityAssembler() {
        return instance;
    }

    public EstadoPuestoDomain toDomain(EstadoPuestoEntity entity) {
        return EstadoPuestoDomain.create(
                entity.getId(),
                entity.getNombre()
        );
    }

    public EstadoPuestoEntity toEntity(EstadoPuestoDomain domain) {
        return EstadoPuestoEntity.create(
                domain.getId(),
                domain.getNombre()
        );
    }

    public List<EstadoPuestoDomain> toDomainList(List<EstadoPuestoEntity> entities) {
        List<EstadoPuestoDomain> domains = new ArrayList<>();
        for (EstadoPuestoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}