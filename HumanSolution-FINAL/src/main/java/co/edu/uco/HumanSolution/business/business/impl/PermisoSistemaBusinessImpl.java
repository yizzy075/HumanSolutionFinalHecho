package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.PermisoSistemaEntityAssembler;
import co.edu.uco.HumanSolution.business.business.PermisoSistemaBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.PermisoSistemaDomain;
import co.edu.uco.HumanSolution.entity.PermisoSistemaEntity;

import java.util.List;
import java.util.UUID;

public final class PermisoSistemaBusinessImpl implements PermisoSistemaBusiness {

    private DAOFactory daoFactory;

    public PermisoSistemaBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(PermisoSistemaDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = PermisoSistemaDomain.create(id, domain.getNombre());

            var entity = PermisoSistemaEntityAssembler.getPermisoSistemaEntityAssembler().toEntity(domainWithId);
            daoFactory.getPermisoSistemaDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando permiso de sistema: " + exception.getMessage(),
                    "Error inesperado al crear permiso de sistema",
                    exception
            );
        }
    }

    @Override
    public List<PermisoSistemaDomain> list() {
        try {
            PermisoSistemaEntity filter = PermisoSistemaEntity.create();
            List<PermisoSistemaEntity> entities = daoFactory.getPermisoSistemaDAO().read(filter);
            return PermisoSistemaEntityAssembler.getPermisoSistemaEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos de sistema: " + exception.getMessage(),
                    "Error inesperado al listar permisos de sistema",
                    exception
            );
        }
    }

    @Override
    public PermisoSistemaDomain findById(UUID id) {
        try {
            PermisoSistemaEntity filter = PermisoSistemaEntity.create(id, "");
            List<PermisoSistemaEntity> entities = daoFactory.getPermisoSistemaDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Permiso de sistema con ID " + id + " no existe",
                        "No se encontró el permiso de sistema"
                );
            }

            return PermisoSistemaEntityAssembler.getPermisoSistemaEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permiso de sistema: " + exception.getMessage(),
                    "Error inesperado al buscar permiso de sistema",
                    exception
            );
        }
    }

    @Override
    public void update(PermisoSistemaDomain domain) {
        try {
            domain.validar();

            var entity = PermisoSistemaEntityAssembler.getPermisoSistemaEntityAssembler().toEntity(domain);
            daoFactory.getPermisoSistemaDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando permiso de sistema: " + exception.getMessage(),
                    "Error inesperado al actualizar permiso de sistema",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getPermisoSistemaDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando permiso de sistema: " + exception.getMessage(),
                    "Error inesperado al eliminar permiso de sistema",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = PermisoSistemaEntity.create(id, "");
        var existing = daoFactory.getPermisoSistemaDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = PermisoSistemaEntity.create(id, "");
            existing = daoFactory.getPermisoSistemaDAO().read(entity);
        }

        return id;
    }
}