package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.UsuarioDocumentoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioDocumentoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.UsuarioDocumentoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.UsuarioDocumentoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.UsuarioDocumentoDTO;

import java.util.List;
import java.util.UUID;

public final class UsuarioDocumentoFacadeImpl implements UsuarioDocumentoFacade {

    private DAOFactory daoFactory;

    public UsuarioDocumentoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(UsuarioDocumentoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioDocumentoDTOAssembler.getUsuarioDocumentoDTOAssembler().toDomain(dto);
            UsuarioDocumentoBusiness business = new UsuarioDocumentoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando documento de usuario: " + exception.getMessage(),
                    "Error al crear documento de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioDocumentoDTO> list() {
        try {
            UsuarioDocumentoBusiness business = new UsuarioDocumentoBusinessImpl(daoFactory);
            var domains = business.list();
            return UsuarioDocumentoDTOAssembler.getUsuarioDocumentoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando documentos de usuarios: " + exception.getMessage(),
                    "Error al listar documentos de usuarios",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioDocumentoDTO> findByUsuario(UUID idUsuario) {
        try {
            UsuarioDocumentoBusiness business = new UsuarioDocumentoBusinessImpl(daoFactory);
            var domains = business.findByUsuario(idUsuario);
            return UsuarioDocumentoDTOAssembler.getUsuarioDocumentoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando documentos por usuario: " + exception.getMessage(),
                    "Error al buscar documentos",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UsuarioDocumentoDTO findById(UUID id) {
        try {
            UsuarioDocumentoBusiness business = new UsuarioDocumentoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return UsuarioDocumentoDTOAssembler.getUsuarioDocumentoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando documento de usuario: " + exception.getMessage(),
                    "Error al buscar documento de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(UsuarioDocumentoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioDocumentoDTOAssembler.getUsuarioDocumentoDTOAssembler().toDomain(dto);
            UsuarioDocumentoBusiness business = new UsuarioDocumentoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando documento de usuario: " + exception.getMessage(),
                    "Error al actualizar documento de usuario",
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

            UsuarioDocumentoBusiness business = new UsuarioDocumentoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando documento de usuario: " + exception.getMessage(),
                    "Error al eliminar documento de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}

