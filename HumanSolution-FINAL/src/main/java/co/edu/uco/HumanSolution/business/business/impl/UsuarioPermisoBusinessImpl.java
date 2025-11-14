package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.UsuarioPermisoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioPermisoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.UsuarioPermisoDomain;
import co.edu.uco.HumanSolution.entity.UsuarioPermisoEntity;

import java.util.List;
import java.util.UUID;

public final class UsuarioPermisoBusinessImpl implements UsuarioPermisoBusiness {

    private DAOFactory daoFactory;

    public UsuarioPermisoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(UsuarioPermisoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = UsuarioPermisoDomain.create(
                    id,
                    domain.getIdUsuario(),
                    domain.getIdTipoPermiso(),
                    domain.getFechaInicio(),
                    domain.getFechaFin(),
                    domain.getIdEstadoSolicitud()
            );

            var entity = UsuarioPermisoEntityAssembler.getUsuarioPermisoEntityAssembler().toEntity(domainWithId);
            daoFactory.getUsuarioPermisoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando permiso de usuario: " + exception.getMessage(),
                    "Error inesperado al crear permiso",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioPermisoDomain> list() {
        try {
            UsuarioPermisoEntity filter = UsuarioPermisoEntity.create();
            List<UsuarioPermisoEntity> entities = daoFactory.getUsuarioPermisoDAO().read(filter);
            return UsuarioPermisoEntityAssembler.getUsuarioPermisoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos de usuarios: " + exception.getMessage(),
                    "Error inesperado al listar permisos",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioPermisoDomain> findByUsuario(UUID idUsuario) {
        try {
            UsuarioPermisoEntity filter = UsuarioPermisoEntity.create(
                    UUIDHelper.getDefaultUUID(),
                    idUsuario,
                    UUIDHelper.getDefaultUUID(),
                    null,
                    null,
                    UUIDHelper.getDefaultUUID()
            );
            List<UsuarioPermisoEntity> entities = daoFactory.getUsuarioPermisoDAO().read(filter);
            return UsuarioPermisoEntityAssembler.getUsuarioPermisoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos por usuario: " + exception.getMessage(),
                    "Error inesperado al buscar permisos",
                    exception
            );
        }
    }

    @Override
    public UsuarioPermisoDomain findById(UUID id) {
        try {
            UsuarioPermisoEntity filter = UsuarioPermisoEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    null,
                    null,
                    UUIDHelper.getDefaultUUID()
            );
            List<UsuarioPermisoEntity> entities = daoFactory.getUsuarioPermisoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Permiso con ID " + id + " no existe",
                        "No se encontró el permiso"
                );
            }

            return UsuarioPermisoEntityAssembler.getUsuarioPermisoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permiso: " + exception.getMessage(),
                    "Error inesperado al buscar permiso",
                    exception
            );
        }
    }

    @Override
    public void update(UsuarioPermisoDomain domain) {
        try {
            domain.validar();

            var entity = UsuarioPermisoEntityAssembler.getUsuarioPermisoEntityAssembler().toEntity(domain);
            daoFactory.getUsuarioPermisoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando permiso: " + exception.getMessage(),
                    "Error inesperado al actualizar permiso",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getUsuarioPermisoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando permiso: " + exception.getMessage(),
                    "Error inesperado al eliminar permiso",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = UsuarioPermisoEntity.create(
                id,
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                null,
                null,
                UUIDHelper.getDefaultUUID()
        );
        var existing = daoFactory.getUsuarioPermisoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = UsuarioPermisoEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    UUIDHelper.getDefaultUUID(),
                    null,
                    null,
                    UUIDHelper.getDefaultUUID()
            );
            existing = daoFactory.getUsuarioPermisoDAO().read(entity);
        }

        return id;
    }
}