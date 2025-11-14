package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.UsuarioPermisoDomain;
import co.edu.uco.HumanSolution.dto.UsuarioPermisoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UsuarioPermisoDTOAssembler implements DTOAssembler<UsuarioPermisoDomain, UsuarioPermisoDTO> {

    private static final DTOAssembler<UsuarioPermisoDomain, UsuarioPermisoDTO> instance = new UsuarioPermisoDTOAssembler();

    private UsuarioPermisoDTOAssembler() {
    }

    public static DTOAssembler<UsuarioPermisoDomain, UsuarioPermisoDTO> getUsuarioPermisoDTOAssembler() {
        return instance;
    }

    @Override
    public UsuarioPermisoDomain toDomain(UsuarioPermisoDTO dto) {
        return UsuarioPermisoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                UUID.fromString(dto.getIdTipoPermiso()),
                dto.getFechaInicio(),  // ✅ LocalDate → LocalDate directo
                dto.getFechaFin(),     // ✅ LocalDate → LocalDate directo
                UUID.fromString(dto.getIdEstadoSolicitud())
        );
    }

    @Override
    public UsuarioPermisoDTO toDTO(UsuarioPermisoDomain domain) {
        return UsuarioPermisoDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getIdTipoPermiso().toString(),
                domain.getFechaInicio(),  // ✅ LocalDate → LocalDate directo
                domain.getFechaFin(),     // ✅ LocalDate → LocalDate directo
                domain.getIdEstadoSolicitud().toString()
        );
    }

    public List<UsuarioPermisoDTO> toDTOList(List<UsuarioPermisoDomain> domains) {
        List<UsuarioPermisoDTO> dtos = new ArrayList<>();
        for (UsuarioPermisoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}