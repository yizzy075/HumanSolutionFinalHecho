package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.TipoDocumentoDomain;
import co.edu.uco.HumanSolution.dto.TipoDocumentoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class TipoDocumentoDTOAssembler implements DTOAssembler<TipoDocumentoDomain, TipoDocumentoDTO> {

    private static final TipoDocumentoDTOAssembler instance = new TipoDocumentoDTOAssembler();

    private TipoDocumentoDTOAssembler() {
    }

    // ✅ RETORNA LA CLASE CONCRETA, NO LA INTERFAZ
    public static TipoDocumentoDTOAssembler getTipoDocumentoDTOAssembler() {
        return instance;
    }

    @Override
    public TipoDocumentoDomain toDomain(TipoDocumentoDTO dto) {
        return TipoDocumentoDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre(),
                dto.getDescripcion()
        );
    }

    @Override
    public TipoDocumentoDTO toDTO(TipoDocumentoDomain domain) {
        return TipoDocumentoDTO.create(
                domain.getId().toString(),
                domain.getNombre(),
                domain.getDescripcion()
        );
    }

    public List<TipoDocumentoDTO> toDTOList(List<TipoDocumentoDomain> domains) {
        List<TipoDocumentoDTO> dtos = new ArrayList<>();
        for (TipoDocumentoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}