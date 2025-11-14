package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.TipoDocumentoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.TipoDocumentoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.TipoDocumentoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.TipoDocumentoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.TipoDocumentoDTO;

import java.util.List;
import java.util.UUID;

public final class TipoDocumentoFacadeImpl implements TipoDocumentoFacade {

    private DAOFactory daoFactory;

    public TipoDocumentoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(TipoDocumentoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = TipoDocumentoDTOAssembler.getTipoDocumentoDTOAssembler().toDomain(dto);
            TipoDocumentoBusiness business = new TipoDocumentoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando tipo de documento: " + exception.getMessage(),
                    "Error al crear tipo de documento",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<TipoDocumentoDTO> list() {
        try {
            TipoDocumentoBusiness business = new TipoDocumentoBusinessImpl(daoFactory);
            var domains = business.list();
            return TipoDocumentoDTOAssembler.getTipoDocumentoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando tipos de documento: " + exception.getMessage(),
                    "Error al listar tipos de documento",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public TipoDocumentoDTO findById(UUID id) {
        try {
            TipoDocumentoBusiness business = new TipoDocumentoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return TipoDocumentoDTOAssembler.getTipoDocumentoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando tipo de documento: " + exception.getMessage(),
                    "Error al buscar tipo de documento",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(TipoDocumentoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = TipoDocumentoDTOAssembler.getTipoDocumentoDTOAssembler().toDomain(dto);
            TipoDocumentoBusiness business = new TipoDocumentoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando tipo de documento: " + exception.getMessage(),
                    "Error al actualizar tipo de documento",
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

            TipoDocumentoBusiness business = new TipoDocumentoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando tipo de documento: " + exception.getMessage(),
                    "Error al eliminar tipo de documento",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}