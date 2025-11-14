package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.EstadoSolicitudEntityAssembler;
import co.edu.uco.HumanSolution.business.business.EstadoSolicitudBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.EstadoSolicitudDomain;
import co.edu.uco.HumanSolution.entity.EstadoSolicitudEntity;

import java.util.List;
import java.util.UUID;

public final class EstadoSolicitudBusinessImpl implements EstadoSolicitudBusiness {

    private DAOFactory daoFactory;

    public EstadoSolicitudBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(EstadoSolicitudDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = EstadoSolicitudDomain.create(id, domain.getNombre());

            var entity = EstadoSolicitudEntityAssembler.getEstadoSolicitudEntityAssembler().toEntity(domainWithId);
            daoFactory.getEstadoSolicitudDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando estado de solicitud: " + exception.getMessage(),
                    "Error inesperado al crear estado de solicitud",
                    exception
            );
        }
    }

    @Override
    public List<EstadoSolicitudDomain> list() {
        try {
            EstadoSolicitudEntity filter = EstadoSolicitudEntity.create();
            List<EstadoSolicitudEntity> entities = daoFactory.getEstadoSolicitudDAO().read(filter);
            return EstadoSolicitudEntityAssembler.getEstadoSolicitudEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estados de solicitud: " + exception.getMessage(),
                    "Error inesperado al listar estados de solicitud",
                    exception
            );
        }
    }

    @Override
    public EstadoSolicitudDomain findById(UUID id) {
        try {
            EstadoSolicitudEntity filter = EstadoSolicitudEntity.create(id, "");
            List<EstadoSolicitudEntity> entities = daoFactory.getEstadoSolicitudDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Estado de solicitud con ID " + id + " no existe",
                        "No se encontró el estado de solicitud"
                );
            }

            return EstadoSolicitudEntityAssembler.getEstadoSolicitudEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estado de solicitud: " + exception.getMessage(),
                    "Error inesperado al buscar estado de solicitud",
                    exception
            );
        }
    }

    @Override
    public void update(EstadoSolicitudDomain domain) {
        try {
            domain.validar();

            var entity = EstadoSolicitudEntityAssembler.getEstadoSolicitudEntityAssembler().toEntity(domain);
            daoFactory.getEstadoSolicitudDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando estado de solicitud: " + exception.getMessage(),
                    "Error inesperado al actualizar estado de solicitud",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getEstadoSolicitudDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando estado de solicitud: " + exception.getMessage(),
                    "Error inesperado al eliminar estado de solicitud",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = EstadoSolicitudEntity.create(id, "");
        var existing = daoFactory.getEstadoSolicitudDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = EstadoSolicitudEntity.create(id, "");
            existing = daoFactory.getEstadoSolicitudDAO().read(entity);
        }

        return id;
    }
}