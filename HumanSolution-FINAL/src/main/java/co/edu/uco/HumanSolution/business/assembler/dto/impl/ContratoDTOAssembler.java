package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.ContratoDomain;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.dto.ContratoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ContratoDTOAssembler implements DTOAssembler<ContratoDomain, ContratoDTO> {

    private static final DTOAssembler<ContratoDomain, ContratoDTO> instance = new ContratoDTOAssembler();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private ContratoDTOAssembler() {
    }

    public static DTOAssembler<ContratoDomain, ContratoDTO> getContratoDTOAssembler() {
        return instance;
    }

    @Override
    public ContratoDomain toDomain(ContratoDTO dto) {
        UUID id = (dto.getId() != null && !dto.getId().isBlank())
                ? UUID.fromString(dto.getId())
                : UUIDHelper.getDefaultUUID();

        UUID idUsuario = (dto.getIdUsuario() != null && !dto.getIdUsuario().isBlank())
                ? UUID.fromString(dto.getIdUsuario())
                : UUIDHelper.getDefaultUUID();

        LocalDate fechaInicio = (dto.getFechaInicio() != null && !dto.getFechaInicio().isBlank())
                ? LocalDate.parse(dto.getFechaInicio(), DATE_FORMATTER)
                : LocalDate.now();

        LocalDate fechaFin = (dto.getFechaFin() != null && !dto.getFechaFin().isBlank())
                ? LocalDate.parse(dto.getFechaFin(), DATE_FORMATTER)
                : null;

        // Convertir String a BigDecimal
        BigDecimal sueldo = (dto.getSueldo() != null && !dto.getSueldo().isBlank())
                ? new BigDecimal(dto.getSueldo())
                : BigDecimal.ZERO;

        return ContratoDomain.create(
                id,
                idUsuario,
                fechaInicio,
                fechaFin,
                sueldo
        );
    }

    @Override
    public ContratoDTO toDTO(ContratoDomain domain) {
        ContratoDTO dto = new ContratoDTO();

        dto.setId(domain.getId() != null ? domain.getId().toString() : null);
        dto.setIdUsuario(domain.getIdUsuario() != null ? domain.getIdUsuario().toString() : null);
        dto.setFechaInicio(domain.getFechaInicio() != null ? domain.getFechaInicio().format(DATE_FORMATTER) : null);
        dto.setFechaFin(domain.getFechaFin() != null ? domain.getFechaFin().format(DATE_FORMATTER) : null);

        // Convertir BigDecimal a String
        dto.setSueldo(domain.getSueldo() != null ? domain.getSueldo().toString() : null);

        return dto;
    }

    @Override
    public List<ContratoDTO> toDTOList(List<ContratoDomain> domains) {
        List<ContratoDTO> dtos = new ArrayList<>();
        if (domains != null) {
            for (ContratoDomain domain : domains) {
                dtos.add(toDTO(domain));
            }
        }
        return dtos;
    }

    @Override
    public List<ContratoDomain> toDomainList(List<ContratoDTO> dtos) {
        List<ContratoDomain> domains = new ArrayList<>();
        if (dtos != null) {
            for (ContratoDTO dto : dtos) {
                domains.add(toDomain(dto));
            }
        }
        return domains;
    }
}