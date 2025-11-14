package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.PuestoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.PuestoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.PuestoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.PuestoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.PuestoDTO;

import java.util.List;
import java.util.UUID;

public final class PuestoFacadeImpl implements PuestoFacade {

    private DAOFactory daoFactory;

    public PuestoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(PuestoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = PuestoDTOAssembler.getPuestoDTOAssembler().toDomain(dto);
            PuestoBusiness business = new PuestoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando puesto: " + exception.getMessage(),
                    "Error al crear puesto",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<PuestoDTO> list() {
        try {
            PuestoBusiness business = new PuestoBusinessImpl(daoFactory);
            var domains = business.list();
            return PuestoDTOAssembler.getPuestoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando puestos: " + exception.getMessage(),
                    "Error al listar puestos",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<PuestoDTO> findByUnidad(UUID idUnidad) {
        try {
            PuestoBusiness business = new PuestoBusinessImpl(daoFactory);
            var domains = business.findByUnidad(idUnidad);
            return PuestoDTOAssembler.getPuestoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando puestos por unidad: " + exception.getMessage(),
                    "Error al buscar puestos",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public PuestoDTO findById(UUID id) {
        try {
            PuestoBusiness business = new PuestoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return PuestoDTOAssembler.getPuestoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando puesto: " + exception.getMessage(),
                    "Error al buscar puesto",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(PuestoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = PuestoDTOAssembler.getPuestoDTOAssembler().toDomain(dto);
            PuestoBusiness business = new PuestoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando puesto: " + exception.getMessage(),
                    "Error al actualizar puesto",
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

            PuestoBusiness business = new PuestoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando puesto: " + exception.getMessage(),
                    "Error al eliminar puesto",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}