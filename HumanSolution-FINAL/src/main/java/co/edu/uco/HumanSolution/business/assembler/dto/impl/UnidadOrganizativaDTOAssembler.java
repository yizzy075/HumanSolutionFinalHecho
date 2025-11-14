package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.UnidadOrganizativaDomain;
import co.edu.uco.HumanSolution.dto.UnidadOrganizativaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UnidadOrganizativaDTOAssembler implements DTOAssembler<UnidadOrganizativaDomain, UnidadOrganizativaDTO> {

    private static final UnidadOrganizativaDTOAssembler instance = new UnidadOrganizativaDTOAssembler();

    private UnidadOrganizativaDTOAssembler() {
    }

    public static UnidadOrganizativaDTOAssembler getUnidadOrganizativaDTOAssembler() {
        return instance;
    }

    @Override
    public UnidadOrganizativaDomain toDomain(UnidadOrganizativaDTO dto) {
        return UnidadOrganizativaDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre(),
                UUID.fromString(dto.getIdUnidadSuperior())
        );
    }

    @Override
    public UnidadOrganizativaDTO toDTO(UnidadOrganizativaDomain domain) {
        return UnidadOrganizativaDTO.create(
                domain.getId().toString(),
                domain.getNombre(),
                domain.getIdUnidadSuperior().toString()
        );
    }

    public List<UnidadOrganizativaDTO> toDTOList(List<UnidadOrganizativaDomain> domains) {
        List<UnidadOrganizativaDTO> dtos = new ArrayList<>();
        for (UnidadOrganizativaDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}