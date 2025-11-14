package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.PuestoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.PuestoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.PuestoDomain;
import co.edu.uco.HumanSolution.entity.PuestoEntity;

import java.util.List;
import java.util.UUID;

public final class PuestoBusinessImpl implements PuestoBusiness {

    private DAOFactory daoFactory;

    public PuestoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(PuestoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = PuestoDomain.create(
                    id,
                    domain.getNombre(),
                    domain.getIdUsuario(),
                    domain.getIdUnidad(),
                    domain.getIdEstado(),
                    domain.getIdJefe()
            );

            var entity = PuestoEntityAssembler.getPuestoEntityAssembler().toEntity(domainWithId);
            daoFactory.getPuestoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando puesto: " + exception.getMessage(),
                    "Error inesperado al crear puesto",
                    exception
            );
        }
    }

    @Override
    public List<PuestoDomain> list() {
        try {
            PuestoEntity filter = PuestoEntity.create();
            List<PuestoEntity> entities = daoFactory.getPuestoDAO().read(filter);
            return PuestoEntityAssembler.getPuestoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando puestos: " + exception.getMessage(),
                    "Error inesperado al listar puestos",
                    exception
            );
        }
    }

    @Override
    public List<PuestoDomain> findByUnidad(UUID idUnidad) {
        try {
            PuestoEntity filter = PuestoEntity.create(
                    UUIDHelper.getDefaultUUID(),
                    "",
                    UUIDHelper.getDefaultUUID(),
                    idUnidad,
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID()
            );
            List<PuestoEntity> entities = daoFactory.getPuestoDAO().read(filter);
            return PuestoEntityAssembler.getPuestoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando puestos por unidad: " + exception.getMessage(),
                    "Error inesperado al buscar puestos",
                    exception
            );
        }
    }

    @Override
    public PuestoDomain findById(UUID id) {
        try {
            PuestoEntity filter = PuestoEntity.create(
                    id,
                    "",
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID()
            );
            List<PuestoEntity> entities = daoFactory.getPuestoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Puesto con ID " + id + " no existe",
                        "No se encontró el puesto"
                );
            }

            return PuestoEntityAssembler.getPuestoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando puesto: " + exception.getMessage(),
                    "Error inesperado al buscar puesto",
                    exception
            );
        }
    }

    @Override
    public void update(PuestoDomain domain) {
        try {
            domain.validar();

            var entity = PuestoEntityAssembler.getPuestoEntityAssembler().toEntity(domain);
            daoFactory.getPuestoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando puesto: " + exception.getMessage(),
                    "Error inesperado al actualizar puesto",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getPuestoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando puesto: " + exception.getMessage(),
                    "Error inesperado al eliminar puesto",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = PuestoEntity.create(
                id,
                "",
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID()
        );
        var existing = daoFactory.getPuestoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = PuestoEntity.create(
                    id,
                    "",
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID()
            );
            existing = daoFactory.getPuestoDAO().read(entity);
        }

        return id;
    }
}