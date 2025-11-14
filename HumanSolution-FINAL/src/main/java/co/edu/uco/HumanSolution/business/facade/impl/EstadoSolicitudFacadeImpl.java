package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.EstadoSolicitudDTOAssembler;
import co.edu.uco.HumanSolution.business.business.EstadoSolicitudBusiness;
import co.edu.uco.HumanSolution.business.business.impl.EstadoSolicitudBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.EstadoSolicitudFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.EstadoSolicitudDTO;

import java.util.List;
import java.util.UUID;

public final class EstadoSolicitudFacadeImpl implements EstadoSolicitudFacade {

    private DAOFactory daoFactory;

    public EstadoSolicitudFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(EstadoSolicitudDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = EstadoSolicitudDTOAssembler.getEstadoSolicitudDTOAssembler().toDomain(dto);
            EstadoSolicitudBusiness business = new EstadoSolicitudBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando estado de solicitud: " + exception.getMessage(),
                    "Error al crear estado de solicitud",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<EstadoSolicitudDTO> list() {
        try {
            EstadoSolicitudBusiness business = new EstadoSolicitudBusinessImpl(daoFactory);
            var domains = business.list();
            return EstadoSolicitudDTOAssembler.getEstadoSolicitudDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando estados de solicitud: " + exception.getMessage(),
                    "Error al listar estados de solicitud",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public EstadoSolicitudDTO findById(UUID id) {
        try {
            EstadoSolicitudBusiness business = new EstadoSolicitudBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return EstadoSolicitudDTOAssembler.getEstadoSolicitudDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando estado de solicitud: " + exception.getMessage(),
                    "Error al buscar estado de solicitud",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(EstadoSolicitudDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = EstadoSolicitudDTOAssembler.getEstadoSolicitudDTOAssembler().toDomain(dto);
            EstadoSolicitudBusiness business = new EstadoSolicitudBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando estado de solicitud: " + exception.getMessage(),
                    "Error al actualizar estado de solicitud",
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

            EstadoSolicitudBusiness business = new EstadoSolicitudBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando estado de solicitud: " + exception.getMessage(),
                    "Error al eliminar estado de solicitud",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}