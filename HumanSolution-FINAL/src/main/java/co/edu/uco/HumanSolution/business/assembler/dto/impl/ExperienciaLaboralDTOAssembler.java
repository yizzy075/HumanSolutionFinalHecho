package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.ExperienciaLaboralDomain;
import co.edu.uco.HumanSolution.dto.ExperienciaLaboralDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ExperienciaLaboralDTOAssembler implements DTOAssembler<ExperienciaLaboralDomain, ExperienciaLaboralDTO> {

    private static final DTOAssembler<ExperienciaLaboralDomain, ExperienciaLaboralDTO> instance = new ExperienciaLaboralDTOAssembler();

    private ExperienciaLaboralDTOAssembler() {
    }

    public static DTOAssembler<ExperienciaLaboralDomain, ExperienciaLaboralDTO> getExperienciaLaboralDTOAssembler() {
        return instance;
    }

    @Override
    public ExperienciaLaboralDomain toDomain(ExperienciaLaboralDTO dto) {
        return ExperienciaLaboralDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                dto.getEmpresa(),
                dto.getCargo(),
                dto.getFechaInicio(),  // ✅ LocalDate → LocalDate directo
                dto.getFechaFin()      // ✅ LocalDate → LocalDate directo
        );
    }

    @Override
    public ExperienciaLaboralDTO toDTO(ExperienciaLaboralDomain domain) {
        return ExperienciaLaboralDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getEmpresa(),
                domain.getCargo(),
                domain.getFechaInicio(),  // ✅ LocalDate → LocalDate directo
                domain.getFechaFin()      // ✅ LocalDate → LocalDate directo
        );
    }

    public List<ExperienciaLaboralDTO> toDTOList(List<ExperienciaLaboralDomain> domains) {
        List<ExperienciaLaboralDTO> dtos = new ArrayList<>();
        for (ExperienciaLaboralDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}