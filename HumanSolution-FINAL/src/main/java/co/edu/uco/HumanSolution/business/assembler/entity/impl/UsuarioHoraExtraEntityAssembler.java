package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.UsuarioHoraExtraDomain;
import co.edu.uco.HumanSolution.entity.UsuarioHoraExtraEntity;

import java.util.ArrayList;
import java.util.List;

public final class UsuarioHoraExtraEntityAssembler {

    private static final UsuarioHoraExtraEntityAssembler instance = new UsuarioHoraExtraEntityAssembler();

    private UsuarioHoraExtraEntityAssembler() {
    }

    public static UsuarioHoraExtraEntityAssembler getUsuarioHoraExtraEntityAssembler() {
        return instance;
    }

    public UsuarioHoraExtraDomain toDomain(UsuarioHoraExtraEntity entity) {
        return UsuarioHoraExtraDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getFecha(),
                entity.getIdEstadoSolicitud(),
                entity.getHoras(),
                entity.getIdTipoHoraExtra()
        );
    }

    public UsuarioHoraExtraEntity toEntity(UsuarioHoraExtraDomain domain) {
        return UsuarioHoraExtraEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getFecha(),
                domain.getIdEstadoSolicitud(),
                domain.getHoras(),
                domain.getIdTipoHoraExtra()
        );
    }

    public List<UsuarioHoraExtraDomain> toDomainList(List<UsuarioHoraExtraEntity> entities) {
        List<UsuarioHoraExtraDomain> domains = new ArrayList<>();
        for (UsuarioHoraExtraEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}