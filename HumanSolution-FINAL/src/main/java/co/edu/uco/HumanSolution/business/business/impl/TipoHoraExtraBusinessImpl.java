package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.TipoHoraExtraEntityAssembler;
import co.edu.uco.HumanSolution.business.business.TipoHoraExtraBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.TipoHoraExtraDomain;
import co.edu.uco.HumanSolution.entity.TipoHoraExtraEntity;

import java.util.List;
import java.util.UUID;

public final class TipoHoraExtraBusinessImpl implements TipoHoraExtraBusiness {

    private DAOFactory daoFactory;

    public TipoHoraExtraBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(TipoHoraExtraDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = TipoHoraExtraDomain.create(id, domain.getNombre(), domain.getRecargo());

            var entity = TipoHoraExtraEntityAssembler.getTipoHoraExtraEntityAssembler().toEntity(domainWithId);
            daoFactory.getTipoHoraExtraDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando tipo de hora extra: " + exception.getMessage(),
                    "Error inesperado al crear tipo de hora extra",
                    exception
            );
        }
    }

    @Override
    public List<TipoHoraExtraDomain> list() {
        try {
            TipoHoraExtraEntity filter = TipoHoraExtraEntity.create();
            List<TipoHoraExtraEntity> entities = daoFactory.getTipoHoraExtraDAO().read(filter);
            return TipoHoraExtraEntityAssembler.getTipoHoraExtraEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipos de hora extra: " + exception.getMessage(),
                    "Error inesperado al listar tipos de hora extra",
                    exception
            );
        }
    }

    @Override
    public TipoHoraExtraDomain findById(UUID id) {
        try {
            TipoHoraExtraEntity filter = TipoHoraExtraEntity.create(id, "", 0);
            List<TipoHoraExtraEntity> entities = daoFactory.getTipoHoraExtraDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Tipo de hora extra con ID " + id + " no existe",
                        "No se encontró el tipo de hora extra"
                );
            }

            return TipoHoraExtraEntityAssembler.getTipoHoraExtraEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipo de hora extra: " + exception.getMessage(),
                    "Error inesperado al buscar tipo de hora extra",
                    exception
            );
        }
    }

    @Override
    public void update(TipoHoraExtraDomain domain) {
        try {
            domain.validar();

            var entity = TipoHoraExtraEntityAssembler.getTipoHoraExtraEntityAssembler().toEntity(domain);
            daoFactory.getTipoHoraExtraDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando tipo de hora extra: " + exception.getMessage(),
                    "Error inesperado al actualizar tipo de hora extra",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getTipoHoraExtraDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando tipo de hora extra: " + exception.getMessage(),
                    "Error inesperado al eliminar tipo de hora extra",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = TipoHoraExtraEntity.create(id, "", 0);
        var existing = daoFactory.getTipoHoraExtraDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = TipoHoraExtraEntity.create(id, "", 0);
            existing = daoFactory.getTipoHoraExtraDAO().read(entity);
        }

        return id;
    }
}