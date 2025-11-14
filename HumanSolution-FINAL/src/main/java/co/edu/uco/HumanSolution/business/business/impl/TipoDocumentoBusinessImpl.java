package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.TipoDocumentoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.TipoDocumentoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.TipoDocumentoDomain;
import co.edu.uco.HumanSolution.entity.TipoDocumentoEntity;

import java.util.List;
import java.util.UUID;

public final class TipoDocumentoBusinessImpl implements TipoDocumentoBusiness {

    private DAOFactory daoFactory;

    public TipoDocumentoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(TipoDocumentoDomain domain) {
        try {
            // Validar dominio
            domain.validar();

            // Generar ID y crear nuevo domain con ese ID
            var id = generateId();
            var domainWithId = TipoDocumentoDomain.create(id, domain.getNombre(), domain.getDescripcion());

            // Ensamblar a Entity
            var entity = TipoDocumentoEntityAssembler.getTipoDocumentoEntityAssembler().toEntity(domainWithId);

            // Crear en base de datos
            daoFactory.getTipoDocumentoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando tipo de documento: " + exception.getMessage(),
                    "Error inesperado al crear tipo de documento",
                    exception
            );
        }
    }

    @Override
    public List<TipoDocumentoDomain> list() {
        try {
            TipoDocumentoEntity filter = TipoDocumentoEntity.create();
            List<TipoDocumentoEntity> entities = daoFactory.getTipoDocumentoDAO().read(filter);
            return TipoDocumentoEntityAssembler.getTipoDocumentoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipos de documento: " + exception.getMessage(),
                    "Error inesperado al listar tipos de documento",
                    exception
            );
        }
    }

    @Override
    public TipoDocumentoDomain findById(UUID id) {
        try {
            TipoDocumentoEntity filter = TipoDocumentoEntity.create(id, "", "");
            List<TipoDocumentoEntity> entities = daoFactory.getTipoDocumentoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Tipo de documento con ID " + id + " no existe",
                        "No se encontró el tipo de documento"
                );
            }

            return TipoDocumentoEntityAssembler.getTipoDocumentoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipo de documento: " + exception.getMessage(),
                    "Error inesperado al buscar tipo de documento",
                    exception
            );
        }
    }

    @Override
    public void update(TipoDocumentoDomain domain) {
        try {
            // Validar dominio
            domain.validar();

            var entity = TipoDocumentoEntityAssembler.getTipoDocumentoEntityAssembler().toEntity(domain);
            daoFactory.getTipoDocumentoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando tipo de documento: " + exception.getMessage(),
                    "Error inesperado al actualizar tipo de documento",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getTipoDocumentoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando tipo de documento: " + exception.getMessage(),
                    "Error inesperado al eliminar tipo de documento",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = TipoDocumentoEntity.create(id, "", "");
        var existing = daoFactory.getTipoDocumentoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = TipoDocumentoEntity.create(id, "", "");
            existing = daoFactory.getTipoDocumentoDAO().read(entity);
        }

        return id;
    }
}