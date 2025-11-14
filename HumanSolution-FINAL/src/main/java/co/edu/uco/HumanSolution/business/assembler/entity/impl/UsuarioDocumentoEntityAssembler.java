package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.domain.UsuarioDocumentoDomain;
import co.edu.uco.HumanSolution.entity.UsuarioDocumentoEntity;

import java.util.ArrayList;
import java.util.List;

public final class UsuarioDocumentoEntityAssembler {

    private static final UsuarioDocumentoEntityAssembler instance = new UsuarioDocumentoEntityAssembler();

    private UsuarioDocumentoEntityAssembler() {
    }

    public static UsuarioDocumentoEntityAssembler getUsuarioDocumentoEntityAssembler() {
        return instance;
    }

    public UsuarioDocumentoDomain toDomain(UsuarioDocumentoEntity entity) {
        return UsuarioDocumentoDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getIdTipoDocumento(),
                entity.getFecha(),
                entity.getIdEstadoSolicitud()
        );
    }

    public UsuarioDocumentoEntity toEntity(UsuarioDocumentoDomain domain) {
        return UsuarioDocumentoEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getIdTipoDocumento(),
                domain.getFecha(),
                domain.getIdEstadoSolicitud()
        );
    }

    public List<UsuarioDocumentoDomain> toDomainList(List<UsuarioDocumentoEntity> entities) {
        List<UsuarioDocumentoDomain> domains = new ArrayList<>();
        for (UsuarioDocumentoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}