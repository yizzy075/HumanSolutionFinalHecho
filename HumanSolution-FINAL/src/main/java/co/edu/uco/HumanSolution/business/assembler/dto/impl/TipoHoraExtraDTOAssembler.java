package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.TipoHoraExtraDomain;
import co.edu.uco.HumanSolution.dto.TipoHoraExtraDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class TipoHoraExtraDTOAssembler implements DTOAssembler<TipoHoraExtraDomain, TipoHoraExtraDTO> {

    private static final TipoHoraExtraDTOAssembler instance = new TipoHoraExtraDTOAssembler();

    private TipoHoraExtraDTOAssembler() {
    }

    public static TipoHoraExtraDTOAssembler getTipoHoraExtraDTOAssembler() {
        return instance;
    }

    @Override
    public TipoHoraExtraDomain toDomain(TipoHoraExtraDTO dto) {
        return TipoHoraExtraDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre(),
                dto.getRecargo()
        );
    }

    @Override
    public TipoHoraExtraDTO toDTO(TipoHoraExtraDomain domain) {
        return TipoHoraExtraDTO.create(
                domain.getId().toString(),
                domain.getNombre(),
                domain.getRecargo()
        );
    }

    public List<TipoHoraExtraDTO> toDTOList(List<TipoHoraExtraDomain> domains) {
        List<TipoHoraExtraDTO> dtos = new ArrayList<>();
        for (TipoHoraExtraDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}