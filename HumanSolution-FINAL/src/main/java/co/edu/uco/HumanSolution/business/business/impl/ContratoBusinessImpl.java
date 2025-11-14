package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.ContratoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.ContratoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.ContratoDomain;
import co.edu.uco.HumanSolution.entity.ContratoEntity;

import java.util.List;
import java.util.UUID;

public final class ContratoBusinessImpl implements ContratoBusiness {

    private DAOFactory daoFactory;

    public ContratoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(ContratoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = ContratoDomain.create(
                    id,
                    domain.getIdUsuario(),
                    domain.getFechaInicio(),
                    domain.getFechaFin(),
                    domain.getSueldo()
            );

            var entity = ContratoEntityAssembler.getContratoEntityAssembler().toEntity(domainWithId);
            daoFactory.getContratoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando contrato: " + exception.getMessage(),
                    "Error inesperado al crear contrato",
                    exception
            );
        }
    }

    @Override
    public List<ContratoDomain> list() {
        try {
            ContratoEntity filter = ContratoEntity.create();
            List<ContratoEntity> entities = daoFactory.getContratoDAO().read(filter);
            return ContratoEntityAssembler.getContratoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando contratos: " + exception.getMessage(),
                    "Error inesperado al listar contratos",
                    exception
            );
        }
    }

    @Override
    public List<ContratoDomain> findByUsuario(UUID idUsuario) {
        try {
            ContratoEntity filter = ContratoEntity.create(UUIDHelper.getDefaultUUID(), idUsuario, null, null, java.math.BigDecimal.ZERO);
            List<ContratoEntity> entities = daoFactory.getContratoDAO().read(filter);
            return ContratoEntityAssembler.getContratoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando contratos por usuario: " + exception.getMessage(),
                    "Error inesperado al buscar contratos",
                    exception
            );
        }
    }

    @Override
    public ContratoDomain findById(UUID id) {
        try {
            ContratoEntity filter = ContratoEntity.create(id, UUIDHelper.getDefaultUUID(), null, null, java.math.BigDecimal.ZERO);
            List<ContratoEntity> entities = daoFactory.getContratoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Contrato con ID " + id + " no existe",
                        "No se encontró el contrato"
                );
            }

            return ContratoEntityAssembler.getContratoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando contrato: " + exception.getMessage(),
                    "Error inesperado al buscar contrato",
                    exception
            );
        }
    }

    @Override
    public void update(ContratoDomain domain) {
        try {
            domain.validar();

            var entity = ContratoEntityAssembler.getContratoEntityAssembler().toEntity(domain);
            daoFactory.getContratoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando contrato: " + exception.getMessage(),
                    "Error inesperado al actualizar contrato",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getContratoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando contrato: " + exception.getMessage(),
                    "Error inesperado al eliminar contrato",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = ContratoEntity.create(id, UUIDHelper.getDefaultUUID(), null, null, java.math.BigDecimal.ZERO);
        var existing = daoFactory.getContratoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = ContratoEntity.create(id, UUIDHelper.getDefaultUUID(), null, null, java.math.BigDecimal.ZERO);
            existing = daoFactory.getContratoDAO().read(entity);
        }

        return id;
    }
}