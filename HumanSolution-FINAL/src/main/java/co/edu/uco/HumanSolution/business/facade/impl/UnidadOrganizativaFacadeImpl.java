package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.UnidadOrganizativaDTOAssembler;
import co.edu.uco.HumanSolution.business.business.UnidadOrganizativaBusiness;
import co.edu.uco.HumanSolution.business.business.impl.UnidadOrganizativaBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.UnidadOrganizativaFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.UnidadOrganizativaDTO;

import java.util.List;
import java.util.UUID;

public final class UnidadOrganizativaFacadeImpl implements UnidadOrganizativaFacade {

    private DAOFactory daoFactory;

    public UnidadOrganizativaFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(UnidadOrganizativaDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UnidadOrganizativaDTOAssembler.getUnidadOrganizativaDTOAssembler().toDomain(dto);
            UnidadOrganizativaBusiness business = new UnidadOrganizativaBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando unidad organizativa: " + exception.getMessage(),
                    "Error al crear unidad organizativa",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UnidadOrganizativaDTO> list() {
        try {
            UnidadOrganizativaBusiness business = new UnidadOrganizativaBusinessImpl(daoFactory);
            var domains = business.list();
            return UnidadOrganizativaDTOAssembler.getUnidadOrganizativaDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando unidades organizativas: " + exception.getMessage(),
                    "Error al listar unidades organizativas",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UnidadOrganizativaDTO findById(UUID id) {
        try {
            UnidadOrganizativaBusiness business = new UnidadOrganizativaBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return UnidadOrganizativaDTOAssembler.getUnidadOrganizativaDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando unidad organizativa: " + exception.getMessage(),
                    "Error al buscar unidad organizativa",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(UnidadOrganizativaDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UnidadOrganizativaDTOAssembler.getUnidadOrganizativaDTOAssembler().toDomain(dto);
            UnidadOrganizativaBusiness business = new UnidadOrganizativaBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando unidad organizativa: " + exception.getMessage(),
                    "Error al actualizar unidad organizativa",
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

            UnidadOrganizativaBusiness business = new UnidadOrganizativaBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando unidad organizativa: " + exception.getMessage(),
                    "Error al eliminar unidad organizativa",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}