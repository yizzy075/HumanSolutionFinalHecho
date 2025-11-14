package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.TipoPermisoDomain;
import co.edu.uco.HumanSolution.dto.TipoPermisoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class TipoPermisoDTOAssembler implements DTOAssembler<TipoPermisoDomain, TipoPermisoDTO> {

    private static final TipoPermisoDTOAssembler instance = new TipoPermisoDTOAssembler();

    private TipoPermisoDTOAssembler() {
    }

    public static TipoPermisoDTOAssembler getTipoPermisoDTOAssembler() {
        return instance;
    }

    @Override
    public TipoPermisoDomain toDomain(TipoPermisoDTO dto) {
        return TipoPermisoDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre(),
                dto.getDescripcion()
        );
    }

    @Override
    public TipoPermisoDTO toDTO(TipoPermisoDomain domain) {
        return TipoPermisoDTO.create(
                domain.getId().toString(),
                domain.getNombre(),
                domain.getDescripcion()
        );
    }

    public List<TipoPermisoDTO> toDTOList(List<TipoPermisoDomain> domains) {
        List<TipoPermisoDTO> dtos = new ArrayList<>();
        for (TipoPermisoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}