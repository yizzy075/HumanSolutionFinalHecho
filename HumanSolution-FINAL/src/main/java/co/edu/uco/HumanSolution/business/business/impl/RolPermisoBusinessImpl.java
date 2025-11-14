package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.RolPermisoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.RolPermisoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.RolPermisoDomain;
import co.edu.uco.HumanSolution.entity.RolPermisoEntity;

import java.util.List;
import java.util.UUID;

public final class RolPermisoBusinessImpl implements RolPermisoBusiness {

    private DAOFactory daoFactory;

    public RolPermisoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(RolPermisoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = RolPermisoDomain.create(id, domain.getIdRol(), domain.getIdPermiso());

            var entity = RolPermisoEntityAssembler.getRolPermisoEntityAssembler().toEntity(domainWithId);
            daoFactory.getRolPermisoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico asignando permiso a rol: " + exception.getMessage(),
                    "Error inesperado al asignar permiso",
                    exception
            );
        }
    }

    @Override
    public List<RolPermisoDomain> list() {
        try {
            RolPermisoEntity filter = RolPermisoEntity.create();
            List<RolPermisoEntity> entities = daoFactory.getRolPermisoDAO().read(filter);
            return RolPermisoEntityAssembler.getRolPermisoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos de roles: " + exception.getMessage(),
                    "Error inesperado al listar permisos de roles",
                    exception
            );
        }
    }

    @Override
    public List<RolPermisoDomain> findByRol(UUID idRol) {
        try {
            RolPermisoEntity filter = RolPermisoEntity.create(UUIDHelper.getDefaultUUID(), idRol, UUIDHelper.getDefaultUUID());
            List<RolPermisoEntity> entities = daoFactory.getRolPermisoDAO().read(filter);
            return RolPermisoEntityAssembler.getRolPermisoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos del rol: " + exception.getMessage(),
                    "Error inesperado al buscar permisos del rol",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getRolPermisoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando permiso de rol: " + exception.getMessage(),
                    "Error inesperado al eliminar permiso de rol",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = RolPermisoEntity.create(id, UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID());
        var existing = daoFactory.getRolPermisoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = RolPermisoEntity.create(id, UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID());
            existing = daoFactory.getRolPermisoDAO().read(entity);
        }

        return id;
    }
}