package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.RolDTOAssembler;
import co.edu.uco.HumanSolution.business.business.RolBusiness;
import co.edu.uco.HumanSolution.business.business.impl.RolBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.RolFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.RolDTO;

import java.util.List;
import java.util.UUID;

public final class RolFacadeImpl implements RolFacade {

    @Override
    public void create(RolDTO dto) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();

        try {
            daoFactory.initTransaction();

            var domain = RolDTOAssembler.getRolDTOAssembler().toDomain(dto);
            RolBusiness business = new RolBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando rol: " + exception.getMessage(),
                    "Error al crear rol",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<RolDTO> list() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();

        try {
            RolBusiness business = new RolBusinessImpl(daoFactory);
            var domains = business.list();
            return RolDTOAssembler.getRolDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando roles: " + exception.getMessage(),
                    "Error al listar roles",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public RolDTO findById(UUID id) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();

        try {
            RolBusiness business = new RolBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return RolDTOAssembler.getRolDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando rol: " + exception.getMessage(),
                    "Error al buscar rol",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(RolDTO dto) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();

        try {
            daoFactory.initTransaction();

            var domain = RolDTOAssembler.getRolDTOAssembler().toDomain(dto);
            RolBusiness business = new RolBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando rol: " + exception.getMessage(),
                    "Error al actualizar rol",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void delete(UUID id) {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();

        try {
            daoFactory.initTransaction();

            RolBusiness business = new RolBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando rol: " + exception.getMessage(),
                    "Error al eliminar rol",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}