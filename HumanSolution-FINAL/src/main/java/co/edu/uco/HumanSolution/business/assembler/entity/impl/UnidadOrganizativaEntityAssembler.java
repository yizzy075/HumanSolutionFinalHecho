package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.UnidadOrganizativaDomain;
import co.edu.uco.HumanSolution.entity.UnidadOrganizativaEntity;

import java.util.ArrayList;
import java.util.List;

public final class UnidadOrganizativaEntityAssembler {

    private static final UnidadOrganizativaEntityAssembler instance = new UnidadOrganizativaEntityAssembler();

    private UnidadOrganizativaEntityAssembler() {
    }

    public static UnidadOrganizativaEntityAssembler getUnidadOrganizativaEntityAssembler() {
        return instance;
    }

    public UnidadOrganizativaDomain toDomain(UnidadOrganizativaEntity entity) {
        return UnidadOrganizativaDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getIdUnidadSuperior()
        );
    }

    public UnidadOrganizativaEntity toEntity(UnidadOrganizativaDomain domain) {
        return UnidadOrganizativaEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getIdUnidadSuperior()
        );
    }

    public List<UnidadOrganizativaDomain> toDomainList(List<UnidadOrganizativaEntity> entities) {
        List<UnidadOrganizativaDomain> domains = new ArrayList<>();
        for (UnidadOrganizativaEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}