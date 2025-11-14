package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.UnidadOrganizativaEntityAssembler;
import co.edu.uco.HumanSolution.business.business.UnidadOrganizativaBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.UnidadOrganizativaDomain;
import co.edu.uco.HumanSolution.entity.UnidadOrganizativaEntity;

import java.util.List;
import java.util.UUID;

public final class UnidadOrganizativaBusinessImpl implements UnidadOrganizativaBusiness {

    private DAOFactory daoFactory;

    public UnidadOrganizativaBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(UnidadOrganizativaDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = UnidadOrganizativaDomain.create(id, domain.getNombre(), domain.getIdUnidadSuperior());

            var entity = UnidadOrganizativaEntityAssembler.getUnidadOrganizativaEntityAssembler().toEntity(domainWithId);
            daoFactory.getUnidadOrganizativaDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando unidad organizativa: " + exception.getMessage(),
                    "Error inesperado al crear unidad organizativa",
                    exception
            );
        }
    }

    @Override
    public List<UnidadOrganizativaDomain> list() {
        try {
            UnidadOrganizativaEntity filter = UnidadOrganizativaEntity.create();
            List<UnidadOrganizativaEntity> entities = daoFactory.getUnidadOrganizativaDAO().read(filter);
            return UnidadOrganizativaEntityAssembler.getUnidadOrganizativaEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando unidades organizativas: " + exception.getMessage(),
                    "Error inesperado al listar unidades organizativas",
                    exception
            );
        }
    }

    @Override
    public UnidadOrganizativaDomain findById(UUID id) {
        try {
            UnidadOrganizativaEntity filter = UnidadOrganizativaEntity.create(id, "", UUIDHelper.getDefaultUUID());
            List<UnidadOrganizativaEntity> entities = daoFactory.getUnidadOrganizativaDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Unidad organizativa con ID " + id + " no existe",
                        "No se encontró la unidad organizativa"
                );
            }

            return UnidadOrganizativaEntityAssembler.getUnidadOrganizativaEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando unidad organizativa: " + exception.getMessage(),
                    "Error inesperado al buscar unidad organizativa",
                    exception
            );
        }
    }

    @Override
    public void update(UnidadOrganizativaDomain domain) {
        try {
            domain.validar();

            var entity = UnidadOrganizativaEntityAssembler.getUnidadOrganizativaEntityAssembler().toEntity(domain);
            daoFactory.getUnidadOrganizativaDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando unidad organizativa: " + exception.getMessage(),
                    "Error inesperado al actualizar unidad organizativa",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getUnidadOrganizativaDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando unidad organizativa: " + exception.getMessage(),
                    "Error inesperado al eliminar unidad organizativa",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = UnidadOrganizativaEntity.create(id, "", UUIDHelper.getDefaultUUID());
        var existing = daoFactory.getUnidadOrganizativaDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = UnidadOrganizativaEntity.create(id, "", UUIDHelper.getDefaultUUID());
            existing = daoFactory.getUnidadOrganizativaDAO().read(entity);
        }

        return id;
    }
}