package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.UsuarioHoraExtraDTOAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioHoraExtraBusiness;
import co.edu.uco.HumanSolution.business.business.impl.UsuarioHoraExtraBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.UsuarioHoraExtraFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.UsuarioHoraExtraDTO;

import java.util.List;
import java.util.UUID;

public final class UsuarioHoraExtraFacadeImpl implements UsuarioHoraExtraFacade {

    private DAOFactory daoFactory;

    public UsuarioHoraExtraFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(UsuarioHoraExtraDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioHoraExtraDTOAssembler.getUsuarioHoraExtraDTOAssembler().toDomain(dto);
            UsuarioHoraExtraBusiness business = new UsuarioHoraExtraBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando hora extra de usuario: " + exception.getMessage(),
                    "Error al crear hora extra de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioHoraExtraDTO> list() {
        try {
            UsuarioHoraExtraBusiness business = new UsuarioHoraExtraBusinessImpl(daoFactory);
            var domains = business.list();
            return UsuarioHoraExtraDTOAssembler.getUsuarioHoraExtraDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando horas extras de usuarios: " + exception.getMessage(),
                    "Error al listar horas extras de usuarios",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioHoraExtraDTO> findByUsuario(UUID idUsuario) {
        try {
            UsuarioHoraExtraBusiness business = new UsuarioHoraExtraBusinessImpl(daoFactory);
            var domains = business.findByUsuario(idUsuario);
            return UsuarioHoraExtraDTOAssembler.getUsuarioHoraExtraDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando horas extras por usuario: " + exception.getMessage(),
                    "Error al buscar horas extras",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UsuarioHoraExtraDTO findById(UUID id) {
        try {
            UsuarioHoraExtraBusiness business = new UsuarioHoraExtraBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return UsuarioHoraExtraDTOAssembler.getUsuarioHoraExtraDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando hora extra de usuario: " + exception.getMessage(),
                    "Error al buscar hora extra de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(UsuarioHoraExtraDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioHoraExtraDTOAssembler.getUsuarioHoraExtraDTOAssembler().toDomain(dto);
            UsuarioHoraExtraBusiness business = new UsuarioHoraExtraBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando hora extra de usuario: " + exception.getMessage(),
                    "Error al actualizar hora extra de usuario",
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

            UsuarioHoraExtraBusiness business = new UsuarioHoraExtraBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando hora extra de usuario: " + exception.getMessage(),
                    "Error al eliminar hora extra de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}

