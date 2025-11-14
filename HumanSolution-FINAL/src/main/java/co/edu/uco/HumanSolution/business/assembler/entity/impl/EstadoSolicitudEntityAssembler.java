package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.EstadoSolicitudDomain;
import co.edu.uco.HumanSolution.entity.EstadoSolicitudEntity;

import java.util.ArrayList;
import java.util.List;

public final class EstadoSolicitudEntityAssembler {

    private static final EstadoSolicitudEntityAssembler instance = new EstadoSolicitudEntityAssembler();

    private EstadoSolicitudEntityAssembler() {
    }

    public static EstadoSolicitudEntityAssembler getEstadoSolicitudEntityAssembler() {
        return instance;
    }

    public EstadoSolicitudDomain toDomain(EstadoSolicitudEntity entity) {
        return EstadoSolicitudDomain.create(
                entity.getId(),
                entity.getNombre()
        );
    }

    public EstadoSolicitudEntity toEntity(EstadoSolicitudDomain domain) {
        return EstadoSolicitudEntity.create(
                domain.getId(),
                domain.getNombre()
        );
    }

    public List<EstadoSolicitudDomain> toDomainList(List<EstadoSolicitudEntity> entities) {
        List<EstadoSolicitudDomain> domains = new ArrayList<>();
        for (EstadoSolicitudEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}