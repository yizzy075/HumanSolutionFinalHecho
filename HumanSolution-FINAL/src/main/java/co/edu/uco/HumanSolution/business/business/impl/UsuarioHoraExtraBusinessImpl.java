package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.UsuarioHoraExtraEntityAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioHoraExtraBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.UsuarioHoraExtraDomain;
import co.edu.uco.HumanSolution.entity.UsuarioHoraExtraEntity;

import java.util.List;
import java.util.UUID;

public final class UsuarioHoraExtraBusinessImpl implements UsuarioHoraExtraBusiness {

    private DAOFactory daoFactory;

    public UsuarioHoraExtraBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(UsuarioHoraExtraDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = UsuarioHoraExtraDomain.create(
                    id,
                    domain.getIdUsuario(),
                    domain.getFecha(),
                    domain.getIdEstadoSolicitud(),
                    domain.getHoras(),
                    domain.getIdTipoHoraExtra()
            );

            var entity = UsuarioHoraExtraEntityAssembler.getUsuarioHoraExtraEntityAssembler().toEntity(domainWithId);
            daoFactory.getUsuarioHoraExtraDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando hora extra: " + exception.getMessage(),
                    "Error inesperado al crear hora extra",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioHoraExtraDomain> list() {
        try {
            UsuarioHoraExtraEntity filter = UsuarioHoraExtraEntity.create();
            List<UsuarioHoraExtraEntity> entities = daoFactory.getUsuarioHoraExtraDAO().read(filter);
            return UsuarioHoraExtraEntityAssembler.getUsuarioHoraExtraEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando horas extras: " + exception.getMessage(),
                    "Error inesperado al listar horas extras",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioHoraExtraDomain> findByUsuario(UUID idUsuario) {
        try {
            UsuarioHoraExtraEntity filter = UsuarioHoraExtraEntity.create(
                    UUIDHelper.getDefaultUUID(),
                    idUsuario,
                    null,
                    UUIDHelper.getDefaultUUID(),
                    0,
                    UUIDHelper.getDefaultUUID()
            );
            List<UsuarioHoraExtraEntity> entities = daoFactory.getUsuarioHoraExtraDAO().read(filter);
            return UsuarioHoraExtraEntityAssembler.getUsuarioHoraExtraEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando horas extras por usuario: " + exception.getMessage(),
                    "Error inesperado al buscar horas extras",
                    exception
            );
        }
    }

    @Override
    public UsuarioHoraExtraDomain findById(UUID id) {
        try {
            UsuarioHoraExtraEntity filter = UsuarioHoraExtraEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    null,
                    UUIDHelper.getDefaultUUID(),
                    0,
                    UUIDHelper.getDefaultUUID()
            );
            List<UsuarioHoraExtraEntity> entities = daoFactory.getUsuarioHoraExtraDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Hora extra con ID " + id + " no existe",
                        "No se encontró la hora extra"
                );
            }

            return UsuarioHoraExtraEntityAssembler.getUsuarioHoraExtraEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando hora extra: " + exception.getMessage(),
                    "Error inesperado al buscar hora extra",
                    exception
            );
        }
    }

    @Override
    public void update(UsuarioHoraExtraDomain domain) {
        try {
            domain.validar();

            var entity = UsuarioHoraExtraEntityAssembler.getUsuarioHoraExtraEntityAssembler().toEntity(domain);
            daoFactory.getUsuarioHoraExtraDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando hora extra: " + exception.getMessage(),
                    "Error inesperado al actualizar hora extra",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getUsuarioHoraExtraDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando hora extra: " + exception.getMessage(),
                    "Error inesperado al eliminar hora extra",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = UsuarioHoraExtraEntity.create(
                id,
                UUIDHelper.getDefaultUUID(),
                null,
                UUIDHelper.getDefaultUUID(),
                0,
                UUIDHelper.getDefaultUUID()
        );
        var existing = daoFactory.getUsuarioHoraExtraDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = UsuarioHoraExtraEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    null,
                    UUIDHelper.getDefaultUUID(),
                    0,
                    UUIDHelper.getDefaultUUID()
            );
            existing = daoFactory.getUsuarioHoraExtraDAO().read(entity);
        }

        return id;
    }
}