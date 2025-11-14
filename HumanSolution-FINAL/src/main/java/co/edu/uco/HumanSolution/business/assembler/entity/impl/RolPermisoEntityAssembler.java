package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.RolPermisoDomain;
import co.edu.uco.HumanSolution.entity.RolPermisoEntity;

import java.util.ArrayList;
import java.util.List;

public final class RolPermisoEntityAssembler {

    private static final RolPermisoEntityAssembler instance = new RolPermisoEntityAssembler();

    private RolPermisoEntityAssembler() {
    }

    public static RolPermisoEntityAssembler getRolPermisoEntityAssembler() {
        return instance;
    }

    public RolPermisoDomain toDomain(RolPermisoEntity entity) {
        return RolPermisoDomain.create(
                entity.getId(),
                entity.getIdRol(),
                entity.getIdPermiso()
        );
    }

    public RolPermisoEntity toEntity(RolPermisoDomain domain) {
        return RolPermisoEntity.create(
                domain.getId(),
                domain.getIdRol(),
                domain.getIdPermiso()
        );
    }

    public List<RolPermisoDomain> toDomainList(List<RolPermisoEntity> entities) {
        List<RolPermisoDomain> domains = new ArrayList<>();
        for (RolPermisoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}