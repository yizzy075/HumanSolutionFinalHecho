package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.RolDomain;
import co.edu.uco.HumanSolution.entity.RolEntity;

import java.util.ArrayList;
import java.util.List;

public final class RolEntityAssembler {

    private static final RolEntityAssembler instance = new RolEntityAssembler();

    public RolEntityAssembler() {
    }

    public static RolEntityAssembler getRolEntityAssembler() {
        return instance;
    }

    public RolDomain toDomain(RolEntity entity) {
        return RolDomain.create(
                entity.getId(),
                entity.getNombre()
        );
    }

    public RolEntity toEntity(RolDomain domain) {
        return RolEntity.create(
                domain.getId(),
                domain.getNombre()
        );
    }

    public List<RolDomain> toDomainList(List<RolEntity> entities) {
        List<RolDomain> domains = new ArrayList<>();
        for (RolEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}