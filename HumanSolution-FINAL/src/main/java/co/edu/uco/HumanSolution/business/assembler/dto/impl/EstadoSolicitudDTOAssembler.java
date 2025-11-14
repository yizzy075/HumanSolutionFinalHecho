package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.EstadoSolicitudDomain;
import co.edu.uco.HumanSolution.dto.EstadoSolicitudDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class EstadoSolicitudDTOAssembler implements DTOAssembler<EstadoSolicitudDomain, EstadoSolicitudDTO> {

    private static final EstadoSolicitudDTOAssembler instance = new EstadoSolicitudDTOAssembler();

    private EstadoSolicitudDTOAssembler() {
    }

    public static EstadoSolicitudDTOAssembler getEstadoSolicitudDTOAssembler() {
        return instance;
    }

    @Override
    public EstadoSolicitudDomain toDomain(EstadoSolicitudDTO dto) {
        return EstadoSolicitudDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre()
        );
    }

    @Override
    public EstadoSolicitudDTO toDTO(EstadoSolicitudDomain domain) {
        return EstadoSolicitudDTO.create(
                domain.getId().toString(),
                domain.getNombre()
        );
    }

    public List<EstadoSolicitudDTO> toDTOList(List<EstadoSolicitudDomain> domains) {
        List<EstadoSolicitudDTO> dtos = new ArrayList<>();
        for (EstadoSolicitudDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}