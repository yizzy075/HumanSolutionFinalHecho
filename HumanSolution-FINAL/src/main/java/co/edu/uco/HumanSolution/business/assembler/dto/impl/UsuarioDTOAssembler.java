package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;
import co.edu.uco.HumanSolution.dto.RolDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UsuarioDTOAssembler {

    private static final UsuarioDTOAssembler instance = new UsuarioDTOAssembler();

    private UsuarioDTOAssembler() {
    }

    public static UsuarioDTOAssembler getUsuarioDTOAssembler() {
        return instance;
    }

    public UsuarioDomain toDomain(UsuarioDTO dto) {
        // Extraer el ID del rol desde el objeto RolDTO
        String idRol = "";
        if (dto.getRol() != null && dto.getRol().getId() != null) {
            idRol = dto.getRol().getId();
        }

        return UsuarioDomain.create(
                dto.getId() != null && !dto.getId().isBlank() ? UUID.fromString(dto.getId()) : null,
                dto.getDocumento(),
                dto.getNombre(),
                dto.getCorreo(),
                dto.getContrasena(),
                idRol  // ✅ String extraído del RolDTO
        );
    }

    public UsuarioDTO toDTO(UsuarioDomain domain) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(domain.getId() != null ? domain.getId().toString() : null);
        dto.setDocumento(domain.getDocumento());
        dto.setNombre(domain.getNombre());
        dto.setCorreo(domain.getCorreo());
        dto.setContrasena(domain.getContrasenia());

        // Crear el objeto RolDTO con solo el ID
        if (domain.getIdRol() != null && !domain.getIdRol().isEmpty()) {
            RolDTO rolDTO = new RolDTO();
            rolDTO.setId(domain.getIdRol());
            dto.setRol(rolDTO);
        }

        return dto;
    }

    public List<UsuarioDomain> toDomainList(List<UsuarioDTO> dtos) {
        List<UsuarioDomain> domains = new ArrayList<>();
        for (UsuarioDTO dto : dtos) {
            domains.add(toDomain(dto));
        }
        return domains;
    }

    public List<UsuarioDTO> toDTOList(List<UsuarioDomain> domains) {
        List<UsuarioDTO> dtos = new ArrayList<>();
        for (UsuarioDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}