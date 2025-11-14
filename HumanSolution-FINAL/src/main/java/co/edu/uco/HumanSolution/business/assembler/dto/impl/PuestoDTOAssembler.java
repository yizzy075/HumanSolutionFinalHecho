package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.PuestoDomain;
import co.edu.uco.HumanSolution.dto.PuestoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class PuestoDTOAssembler implements DTOAssembler<PuestoDomain, PuestoDTO> {

    private static final DTOAssembler<PuestoDomain, PuestoDTO> instance = new PuestoDTOAssembler();

    private PuestoDTOAssembler() {
    }

    public static DTOAssembler<PuestoDomain, PuestoDTO> getPuestoDTOAssembler() {
        return instance;
    }

    @Override
    public PuestoDomain toDomain(PuestoDTO dto) {
        return PuestoDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre(),
                UUID.fromString(dto.getIdUsuario()),
                UUID.fromString(dto.getIdUnidad()),
                UUID.fromString(dto.getIdEstado()),
                UUID.fromString(dto.getIdJefe())
        );
    }

    @Override
    public PuestoDTO toDTO(PuestoDomain domain) {
        return PuestoDTO.create(
                domain.getId().toString(),
                domain.getNombre(),
                domain.getIdUsuario().toString(),
                domain.getIdUnidad().toString(),
                domain.getIdEstado().toString(),
                domain.getIdJefe().toString()
        );
    }

    public List<PuestoDTO> toDTOList(List<PuestoDomain> domains) {
        List<PuestoDTO> dtos = new ArrayList<>();
        for (PuestoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}