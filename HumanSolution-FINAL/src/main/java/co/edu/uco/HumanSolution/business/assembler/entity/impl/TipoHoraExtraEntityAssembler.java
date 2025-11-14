package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.TipoHoraExtraDomain;
import co.edu.uco.HumanSolution.entity.TipoHoraExtraEntity;

import java.util.ArrayList;
import java.util.List;

public final class TipoHoraExtraEntityAssembler {

    private static final TipoHoraExtraEntityAssembler instance = new TipoHoraExtraEntityAssembler();

    private TipoHoraExtraEntityAssembler() {
    }

    public static TipoHoraExtraEntityAssembler getTipoHoraExtraEntityAssembler() {
        return instance;
    }

    public TipoHoraExtraDomain toDomain(TipoHoraExtraEntity entity) {
        return TipoHoraExtraDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getRecargo()
        );
    }

    public TipoHoraExtraEntity toEntity(TipoHoraExtraDomain domain) {
        return TipoHoraExtraEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getRecargo()
        );
    }

    public List<TipoHoraExtraDomain> toDomainList(List<TipoHoraExtraEntity> entities) {
        List<TipoHoraExtraDomain> domains = new ArrayList<>();
        for (TipoHoraExtraEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}