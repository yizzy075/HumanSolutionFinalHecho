package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.UsuarioHoraExtraDomain;
import co.edu.uco.HumanSolution.dto.UsuarioHoraExtraDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UsuarioHoraExtraDTOAssembler implements DTOAssembler<UsuarioHoraExtraDomain, UsuarioHoraExtraDTO> {

    private static final DTOAssembler<UsuarioHoraExtraDomain, UsuarioHoraExtraDTO> instance = new UsuarioHoraExtraDTOAssembler();

    private UsuarioHoraExtraDTOAssembler() {
    }

    public static DTOAssembler<UsuarioHoraExtraDomain, UsuarioHoraExtraDTO> getUsuarioHoraExtraDTOAssembler() {
        return instance;
    }

    @Override
    public UsuarioHoraExtraDomain toDomain(UsuarioHoraExtraDTO dto) {
        return UsuarioHoraExtraDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                dto.getFecha(),  // ✅ LocalDate → LocalDate directo
                UUID.fromString(dto.getIdEstadoSolicitud()),
                dto.getHoras(),
                UUID.fromString(dto.getIdTipoHoraExtra())
        );
    }

    @Override
    public UsuarioHoraExtraDTO toDTO(UsuarioHoraExtraDomain domain) {
        return UsuarioHoraExtraDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getFecha(),  // ✅ LocalDate → LocalDate directo
                domain.getIdEstadoSolicitud().toString(),
                domain.getHoras(),
                domain.getIdTipoHoraExtra().toString()
        );
    }

    public List<UsuarioHoraExtraDTO> toDTOList(List<UsuarioHoraExtraDomain> domains) {
        List<UsuarioHoraExtraDTO> dtos = new ArrayList<>();
        for (UsuarioHoraExtraDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}