package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.PermisoSistemaDomain;
import co.edu.uco.HumanSolution.entity.PermisoSistemaEntity;

import java.util.ArrayList;
import java.util.List;

public final class PermisoSistemaEntityAssembler {

    private static final PermisoSistemaEntityAssembler instance = new PermisoSistemaEntityAssembler();

    private PermisoSistemaEntityAssembler() {
    }

    public static PermisoSistemaEntityAssembler getPermisoSistemaEntityAssembler() {
        return instance;
    }

    public PermisoSistemaDomain toDomain(PermisoSistemaEntity entity) {
        return PermisoSistemaDomain.create(
                entity.getId(),
                entity.getNombre()
        );
    }

    public PermisoSistemaEntity toEntity(PermisoSistemaDomain domain) {
        return PermisoSistemaEntity.create(
                domain.getId(),
                domain.getNombre()
        );
    }

    public List<PermisoSistemaDomain> toDomainList(List<PermisoSistemaEntity> entities) {
        List<PermisoSistemaDomain> domains = new ArrayList<>();
        for (PermisoSistemaEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}