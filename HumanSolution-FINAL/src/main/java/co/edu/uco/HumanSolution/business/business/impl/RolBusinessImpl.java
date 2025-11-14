package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.RolEntityAssembler;
import co.edu.uco.HumanSolution.business.business.RolBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.RolDomain;
import co.edu.uco.HumanSolution.entity.RolEntity;

import java.util.List;
import java.util.UUID;

public class RolBusinessImpl implements RolBusiness {

    private DAOFactory daoFactory;

    public RolBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(RolDomain domain) {
        try {
            domain.validar();

            if (daoFactory.getRolDAO().existsByNombre(domain.getNombre())) {
                throw new HumanSolutionException(
                        "Ya existe un rol con ese nombre",
                        "El nombre ya está registrado"
                );
            }

            daoFactory.initTransaction();

            var entity = RolEntityAssembler.getRolEntityAssembler().toEntity(domain);
            daoFactory.getRolDAO().create(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico creando rol",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<RolDomain> list() {
        try {
            RolEntity filter = RolEntity.create();
            List<RolEntity> entities = daoFactory.getRolDAO().read(filter);
            return RolEntityAssembler.getRolEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando roles",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public RolDomain findById(UUID id) {
        try {
            RolEntity filter = RolEntity.create(id, "");
            List<RolEntity> entities = daoFactory.getRolDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "No se encontró el rol",
                        "Rol no existe"
                );
            }

            return RolEntityAssembler.getRolEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando rol",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(RolDomain domain) {
        try {
            domain.validar();

            daoFactory.initTransaction();

            var entity = RolEntityAssembler.getRolEntityAssembler().toEntity(domain);
            daoFactory.getRolDAO().update(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico actualizando rol",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.initTransaction();
            daoFactory.getRolDAO().delete(id);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico eliminando rol",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}