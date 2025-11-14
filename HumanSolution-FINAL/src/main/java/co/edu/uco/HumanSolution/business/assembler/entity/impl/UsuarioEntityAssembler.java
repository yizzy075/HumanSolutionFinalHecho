package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public final class UsuarioEntityAssembler {

    private static final UsuarioEntityAssembler instance = new UsuarioEntityAssembler();

    private UsuarioEntityAssembler() {
    }

    public static UsuarioEntityAssembler getUsuarioEntityAssembler() {
        return instance;
    }

    public UsuarioDomain toDomain(UsuarioEntity entity) {
        return UsuarioDomain.create(
                entity.getId(),
                entity.getDocumento(),
                entity.getNombre(),
                entity.getCorreo(),
                entity.getContrasenia(),
                entity.getIdRol()
        );
    }

    public UsuarioEntity toEntity(UsuarioDomain domain) {
        return UsuarioEntity.create(
                domain.getId(),
                domain.getDocumento(),
                domain.getNombre(),
                domain.getCorreo(),
                domain.getContrasenia(),
                domain.getIdRol()
        );
    }

    public List<UsuarioDomain> toDomainList(List<UsuarioEntity> entities) {
        List<UsuarioDomain> domains = new ArrayList<>();
        for (UsuarioEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}