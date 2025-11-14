package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.TipoPermisoDomain;
import co.edu.uco.HumanSolution.entity.TipoPermisoEntity;

import java.util.ArrayList;
import java.util.List;

public final class TipoPermisoEntityAssembler {

    private static final TipoPermisoEntityAssembler instance = new TipoPermisoEntityAssembler();

    private TipoPermisoEntityAssembler() {
    }

    public static TipoPermisoEntityAssembler getTipoPermisoEntityAssembler() {
        return instance;
    }

    public TipoPermisoDomain toDomain(TipoPermisoEntity entity) {
        return TipoPermisoDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion()
        );
    }

    public TipoPermisoEntity toEntity(TipoPermisoDomain domain) {
        return TipoPermisoEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getDescripcion()
        );
    }

    public List<TipoPermisoDomain> toDomainList(List<TipoPermisoEntity> entities) {
        List<TipoPermisoDomain> domains = new ArrayList<>();
        for (TipoPermisoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}