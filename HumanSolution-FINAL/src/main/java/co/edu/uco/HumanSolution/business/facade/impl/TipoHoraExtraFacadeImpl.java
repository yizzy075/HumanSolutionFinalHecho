package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.TipoHoraExtraDTOAssembler;
import co.edu.uco.HumanSolution.business.business.TipoHoraExtraBusiness;
import co.edu.uco.HumanSolution.business.business.impl.TipoHoraExtraBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.TipoHoraExtraFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.TipoHoraExtraDTO;

import java.util.List;
import java.util.UUID;

public final class TipoHoraExtraFacadeImpl implements TipoHoraExtraFacade {

    private DAOFactory daoFactory;

    public TipoHoraExtraFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(TipoHoraExtraDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = TipoHoraExtraDTOAssembler.getTipoHoraExtraDTOAssembler().toDomain(dto);
            TipoHoraExtraBusiness business = new TipoHoraExtraBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando tipo de hora extra: " + exception.getMessage(),
                    "Error al crear tipo de hora extra",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<TipoHoraExtraDTO> list() {
        try {
            TipoHoraExtraBusiness business = new TipoHoraExtraBusinessImpl(daoFactory);
            var domains = business.list();
            return TipoHoraExtraDTOAssembler.getTipoHoraExtraDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando tipos de hora extra: " + exception.getMessage(),
                    "Error al listar tipos de hora extra",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public TipoHoraExtraDTO findById(UUID id) {
        try {
            TipoHoraExtraBusiness business = new TipoHoraExtraBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return TipoHoraExtraDTOAssembler.getTipoHoraExtraDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando tipo de hora extra: " + exception.getMessage(),
                    "Error al buscar tipo de hora extra",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(TipoHoraExtraDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = TipoHoraExtraDTOAssembler.getTipoHoraExtraDTOAssembler().toDomain(dto);
            TipoHoraExtraBusiness business = new TipoHoraExtraBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando tipo de hora extra: " + exception.getMessage(),
                    "Error al actualizar tipo de hora extra",
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

            TipoHoraExtraBusiness business = new TipoHoraExtraBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando tipo de hora extra: " + exception.getMessage(),
                    "Error al eliminar tipo de hora extra",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}