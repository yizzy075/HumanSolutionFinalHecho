package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.UsuarioDocumentoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioDocumentoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.UsuarioDocumentoDomain;
import co.edu.uco.HumanSolution.entity.UsuarioDocumentoEntity;

import java.util.List;
import java.util.UUID;

public final class UsuarioDocumentoBusinessImpl implements UsuarioDocumentoBusiness {

    private DAOFactory daoFactory;

    public UsuarioDocumentoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(UsuarioDocumentoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = UsuarioDocumentoDomain.create(
                    id,
                    domain.getIdUsuario(),
                    domain.getIdTipoDocumento(),
                    domain.getFecha(),
                    domain.getIdEstadoSolicitud()
            );

            var entity = UsuarioDocumentoEntityAssembler.getUsuarioDocumentoEntityAssembler().toEntity(domainWithId);
            daoFactory.getUsuarioDocumentoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando documento de usuario: " + exception.getMessage(),
                    "Error inesperado al crear documento",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioDocumentoDomain> list() {
        try {
            UsuarioDocumentoEntity filter = UsuarioDocumentoEntity.create();
            List<UsuarioDocumentoEntity> entities = daoFactory.getUsuarioDocumentoDAO().read(filter);
            return UsuarioDocumentoEntityAssembler.getUsuarioDocumentoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando documentos de usuarios: " + exception.getMessage(),
                    "Error inesperado al listar documentos",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioDocumentoDomain> findByUsuario(UUID idUsuario) {
        try {
            UsuarioDocumentoEntity filter = UsuarioDocumentoEntity.create(
                    UUIDHelper.getDefaultUUID(),
                    idUsuario,
                    UUIDHelper.getDefaultUUID(),
                    null,
                    UUIDHelper.getDefaultUUID()
            );
            List<UsuarioDocumentoEntity> entities = daoFactory.getUsuarioDocumentoDAO().read(filter);
            return UsuarioDocumentoEntityAssembler.getUsuarioDocumentoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando documentos por usuario: " + exception.getMessage(),
                    "Error inesperado al buscar documentos",
                    exception
            );
        }
    }

    @Override
    public UsuarioDocumentoDomain findById(UUID id) {
        try {
            UsuarioDocumentoEntity filter = UsuarioDocumentoEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    null,
                    UUIDHelper.getDefaultUUID()
            );
            List<UsuarioDocumentoEntity> entities = daoFactory.getUsuarioDocumentoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Documento con ID " + id + " no existe",
                        "No se encontró el documento"
                );
            }

            return UsuarioDocumentoEntityAssembler.getUsuarioDocumentoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando documento: " + exception.getMessage(),
                    "Error inesperado al buscar documento",
                    exception
            );
        }
    }

    @Override
    public void update(UsuarioDocumentoDomain domain) {
        try {
            domain.validar();

            var entity = UsuarioDocumentoEntityAssembler.getUsuarioDocumentoEntityAssembler().toEntity(domain);
            daoFactory.getUsuarioDocumentoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando documento: " + exception.getMessage(),
                    "Error inesperado al actualizar documento",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getUsuarioDocumentoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando documento: " + exception.getMessage(),
                    "Error inesperado al eliminar documento",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = UsuarioDocumentoEntity.create(
                id,
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                null,
                UUIDHelper.getDefaultUUID()
        );
        var existing = daoFactory.getUsuarioDocumentoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = UsuarioDocumentoEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    null,
                    UUIDHelper.getDefaultUUID()
            );
            existing = daoFactory.getUsuarioDocumentoDAO().read(entity);
        }

        return id;
    }
}