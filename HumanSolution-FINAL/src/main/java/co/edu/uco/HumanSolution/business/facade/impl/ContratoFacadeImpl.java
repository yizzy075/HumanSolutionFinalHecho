package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.ContratoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.ContratoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.ContratoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.ContratoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.ContratoDTO;

import java.util.List;
import java.util.UUID;

public final class ContratoFacadeImpl implements ContratoFacade {

    private DAOFactory daoFactory;

    public ContratoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(ContratoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = ContratoDTOAssembler.getContratoDTOAssembler().toDomain(dto);
            ContratoBusiness business = new ContratoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando contrato: " + exception.getMessage(),
                    "Error al crear contrato",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<ContratoDTO> list() {
        try {
            ContratoBusiness business = new ContratoBusinessImpl(daoFactory);
            var domains = business.list();
            return ContratoDTOAssembler.getContratoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando contratos: " + exception.getMessage(),
                    "Error al listar contratos",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<ContratoDTO> findByUsuario(UUID idUsuario) {
        try {
            ContratoBusiness business = new ContratoBusinessImpl(daoFactory);
            var domains = business.findByUsuario(idUsuario);
            return ContratoDTOAssembler.getContratoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando contratos por usuario: " + exception.getMessage(),
                    "Error al buscar contratos",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public ContratoDTO findById(UUID id) {
        try {
            ContratoBusiness business = new ContratoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return ContratoDTOAssembler.getContratoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando contrato: " + exception.getMessage(),
                    "Error al buscar contrato",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(ContratoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = ContratoDTOAssembler.getContratoDTOAssembler().toDomain(dto);
            ContratoBusiness business = new ContratoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando contrato: " + exception.getMessage(),
                    "Error al actualizar contrato",
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

            ContratoBusiness business = new ContratoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando contrato: " + exception.getMessage(),
                    "Error al eliminar contrato",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}