package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.EstadoPuestoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.EstadoPuestoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.EstadoPuestoDomain;
import co.edu.uco.HumanSolution.entity.EstadoPuestoEntity;

import java.util.List;
import java.util.UUID;

public final class EstadoPuestoBusinessImpl implements EstadoPuestoBusiness {

    private DAOFactory daoFactory;

    public EstadoPuestoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(EstadoPuestoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = EstadoPuestoDomain.create(id, domain.getNombre());

            var entity = EstadoPuestoEntityAssembler.getEstadoPuestoEntityAssembler().toEntity(domainWithId);
            daoFactory.getEstadoPuestoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando estado de puesto: " + exception.getMessage(),
                    "Error inesperado al crear estado de puesto",
                    exception
            );
        }
    }

    @Override
    public List<EstadoPuestoDomain> list() {
        try {
            EstadoPuestoEntity filter = EstadoPuestoEntity.create();
            List<EstadoPuestoEntity> entities = daoFactory.getEstadoPuestoDAO().read(filter);
            return EstadoPuestoEntityAssembler.getEstadoPuestoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estados de puesto: " + exception.getMessage(),
                    "Error inesperado al listar estados de puesto",
                    exception
            );
        }
    }

    @Override
    public EstadoPuestoDomain findById(UUID id) {
        try {
            EstadoPuestoEntity filter = EstadoPuestoEntity.create(id, "");
            List<EstadoPuestoEntity> entities = daoFactory.getEstadoPuestoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Estado de puesto con ID " + id + " no existe",
                        "No se encontró el estado de puesto"
                );
            }

            return EstadoPuestoEntityAssembler.getEstadoPuestoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estado de puesto: " + exception.getMessage(),
                    "Error inesperado al buscar estado de puesto",
                    exception
            );
        }
    }

    @Override
    public void update(EstadoPuestoDomain domain) {
        try {
            domain.validar();

            var entity = EstadoPuestoEntityAssembler.getEstadoPuestoEntityAssembler().toEntity(domain);
            daoFactory.getEstadoPuestoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando estado de puesto: " + exception.getMessage(),
                    "Error inesperado al actualizar estado de puesto",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getEstadoPuestoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando estado de puesto: " + exception.getMessage(),
                    "Error inesperado al eliminar estado de puesto",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = EstadoPuestoEntity.create(id, "");
        var existing = daoFactory.getEstadoPuestoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = EstadoPuestoEntity.create(id, "");
            existing = daoFactory.getEstadoPuestoDAO().read(entity);
        }

        return id;
    }
}