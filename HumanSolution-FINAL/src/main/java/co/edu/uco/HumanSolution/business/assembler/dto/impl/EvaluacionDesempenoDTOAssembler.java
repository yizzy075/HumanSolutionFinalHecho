package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.dto.ContratoDTO;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class EvaluacionDesempenoDTOAssembler implements DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> {

    private static final EvaluacionDesempenoDTOAssembler INSTANCE = new EvaluacionDesempenoDTOAssembler();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private EvaluacionDesempenoDTOAssembler() {
    }

    public static EvaluacionDesempenoDTOAssembler getEvaluacionDesempenoDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public EvaluacionDesempenoDomain toDomain(EvaluacionDesempenoDTO dto) {
        // Extraer ID del usuario si existe
        UUID idUsuario = null;
        if (dto.getUsuario() != null && dto.getUsuario().getId() != null && !dto.getUsuario().getId().isBlank()) {
            idUsuario = UUID.fromString(dto.getUsuario().getId());
        }

        // Extraer ID del evaluador si existe
        UUID idEvaluador = null;
        if (dto.getEvaluador() != null && dto.getEvaluador().getId() != null && !dto.getEvaluador().getId().isBlank()) {
            idEvaluador = UUID.fromString(dto.getEvaluador().getId());
        }

        // Extraer ID del contrato si existe
        UUID idContrato = null;
        if (dto.getContrato() != null && dto.getContrato().getId() != null && !dto.getContrato().getId().isBlank()) {
            idContrato = UUID.fromString(dto.getContrato().getId());
        }

        // Convertir fecha de String a LocalDate
        LocalDate fecha = null;
        if (dto.getFecha() != null && !dto.getFecha().isBlank()) {
            fecha = LocalDate.parse(dto.getFecha(), DATE_FORMATTER);
        }

        return EvaluacionDesempenoDomain.create(
                dto.getId() != null && !dto.getId().isBlank() ? UUID.fromString(dto.getId()) : null,
                idUsuario,
                idEvaluador,
                idContrato,
                fecha,
                dto.getCalificacion(),
                dto.getObservacion(),
                dto.getCriterios()
        );
    }

    @Override
    public EvaluacionDesempenoDTO toDTO(EvaluacionDesempenoDomain domain) {
        EvaluacionDesempenoDTO dto = new EvaluacionDesempenoDTO();

        dto.setId(domain.getId() != null ? domain.getId().toString() : null);

        // Crear UsuarioDTO con solo el ID
        if (domain.getIdUsuario() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(domain.getIdUsuario().toString());
            dto.setUsuario(usuarioDTO);
        }

        // Crear UsuarioDTO para evaluador con solo el ID
        if (domain.getIdEvaluador() != null) {
            UsuarioDTO evaluadorDTO = new UsuarioDTO();
            evaluadorDTO.setId(domain.getIdEvaluador().toString());
            dto.setEvaluador(evaluadorDTO);
        }

        // Crear ContratoDTO con solo el ID
        if (domain.getIdContrato() != null) {
            ContratoDTO contratoDTO = new ContratoDTO();
            contratoDTO.setId(domain.getIdContrato().toString());
            dto.setContrato(contratoDTO);
        }

        // Convertir fecha de LocalDate a String
        if (domain.getFecha() != null) {
            dto.setFecha(domain.getFecha().format(DATE_FORMATTER));
        }

        dto.setCalificacion(domain.getCalificacion());
        dto.setObservacion(domain.getObservacion());
        dto.setCriterios(domain.getCriterios());

        return dto;
    }

    @Override
    public List<EvaluacionDesempenoDTO> toDTOList(List<EvaluacionDesempenoDomain> domainList) {
        List<EvaluacionDesempenoDTO> dtoList = new ArrayList<>();

        if (domainList != null) {
            for (EvaluacionDesempenoDomain domain : domainList) {
                dtoList.add(toDTO(domain));
            }
        }

        return dtoList;
    }

    @Override
    public List<EvaluacionDesempenoDomain> toDomainList(List<EvaluacionDesempenoDTO> dtoList) {
        List<EvaluacionDesempenoDomain> domainList = new ArrayList<>();

        if (dtoList != null) {
            for (EvaluacionDesempenoDTO dto : dtoList) {
                domainList.add(toDomain(dto));
            }
        }

        return domainList;
    }
}
