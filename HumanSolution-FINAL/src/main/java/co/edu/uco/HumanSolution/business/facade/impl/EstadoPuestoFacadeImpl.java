package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.EstadoPuestoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.EstadoPuestoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.EstadoPuestoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.EstadoPuestoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.EstadoPuestoDTO;

import java.util.List;
import java.util.UUID;

public final class EstadoPuestoFacadeImpl implements EstadoPuestoFacade {

    private DAOFactory daoFactory;

    public EstadoPuestoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(EstadoPuestoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = EstadoPuestoDTOAssembler.getEstadoPuestoDTOAssembler().toDomain(dto);
            EstadoPuestoBusiness business = new EstadoPuestoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando estado de puesto: " + exception.getMessage(),
                    "Error al crear estado de puesto",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<EstadoPuestoDTO> list() {
        try {
            EstadoPuestoBusiness business = new EstadoPuestoBusinessImpl(daoFactory);
            var domains = business.list();
            return EstadoPuestoDTOAssembler.getEstadoPuestoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando estados de puesto: " + exception.getMessage(),
                    "Error al listar estados de puesto",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public EstadoPuestoDTO findById(UUID id) {
        try {
            EstadoPuestoBusiness business = new EstadoPuestoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return EstadoPuestoDTOAssembler.getEstadoPuestoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando estado de puesto: " + exception.getMessage(),
                    "Error al buscar estado de puesto",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(EstadoPuestoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = EstadoPuestoDTOAssembler.getEstadoPuestoDTOAssembler().toDomain(dto);
            EstadoPuestoBusiness business = new EstadoPuestoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando estado de puesto: " + exception.getMessage(),
                    "Error al actualizar estado de puesto",
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

            EstadoPuestoBusiness business = new EstadoPuestoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando estado de puesto: " + exception.getMessage(),
                    "Error al eliminar estado de puesto",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}