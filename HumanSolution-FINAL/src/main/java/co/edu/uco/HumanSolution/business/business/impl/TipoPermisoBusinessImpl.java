package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.TipoPermisoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.TipoPermisoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.TipoPermisoDomain;
import co.edu.uco.HumanSolution.entity.TipoPermisoEntity;

import java.util.List;
import java.util.UUID;

public final class TipoPermisoBusinessImpl implements TipoPermisoBusiness {

    private DAOFactory daoFactory;

    public TipoPermisoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(TipoPermisoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = TipoPermisoDomain.create(id, domain.getNombre(), domain.getDescripcion());

            var entity = TipoPermisoEntityAssembler.getTipoPermisoEntityAssembler().toEntity(domainWithId);
            daoFactory.getTipoPermisoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando tipo de permiso: " + exception.getMessage(),
                    "Error inesperado al crear tipo de permiso",
                    exception
            );
        }
    }

    @Override
    public List<TipoPermisoDomain> list() {
        try {
            TipoPermisoEntity filter = TipoPermisoEntity.create();
            List<TipoPermisoEntity> entities = daoFactory.getTipoPermisoDAO().read(filter);
            return TipoPermisoEntityAssembler.getTipoPermisoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipos de permiso: " + exception.getMessage(),
                    "Error inesperado al listar tipos de permiso",
                    exception
            );
        }
    }

    @Override
    public TipoPermisoDomain findById(UUID id) {
        try {
            TipoPermisoEntity filter = TipoPermisoEntity.create(id, "", "");
            List<TipoPermisoEntity> entities = daoFactory.getTipoPermisoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Tipo de permiso con ID " + id + " no existe",
                        "No se encontró el tipo de permiso"
                );
            }

            return TipoPermisoEntityAssembler.getTipoPermisoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipo de permiso: " + exception.getMessage(),
                    "Error inesperado al buscar tipo de permiso",
                    exception
            );
        }
    }

    @Override
    public void update(TipoPermisoDomain domain) {
        try {
            domain.validar();

            var entity = TipoPermisoEntityAssembler.getTipoPermisoEntityAssembler().toEntity(domain);
            daoFactory.getTipoPermisoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando tipo de permiso: " + exception.getMessage(),
                    "Error inesperado al actualizar tipo de permiso",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getTipoPermisoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando tipo de permiso: " + exception.getMessage(),
                    "Error inesperado al eliminar tipo de permiso",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = TipoPermisoEntity.create(id, "", "");
        var existing = daoFactory.getTipoPermisoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = TipoPermisoEntity.create(id, "", "");
            existing = daoFactory.getTipoPermisoDAO().read(entity);
        }

        return id;
    }
}