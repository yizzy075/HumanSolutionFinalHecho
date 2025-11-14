package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.UsuarioPermisoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioPermisoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.UsuarioPermisoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.UsuarioPermisoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.UsuarioPermisoDTO;

import java.util.List;
import java.util.UUID;

public final class UsuarioPermisoFacadeImpl implements UsuarioPermisoFacade {

    private DAOFactory daoFactory;

    public UsuarioPermisoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(UsuarioPermisoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioPermisoDTOAssembler.getUsuarioPermisoDTOAssembler().toDomain(dto);
            UsuarioPermisoBusiness business = new UsuarioPermisoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando permiso de usuario: " + exception.getMessage(),
                    "Error al crear permiso de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioPermisoDTO> list() {
        try {
            UsuarioPermisoBusiness business = new UsuarioPermisoBusinessImpl(daoFactory);
            var domains = business.list();
            return UsuarioPermisoDTOAssembler.getUsuarioPermisoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando permisos de usuarios: " + exception.getMessage(),
                    "Error al listar permisos de usuarios",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioPermisoDTO> findByUsuario(UUID idUsuario) {
        try {
            UsuarioPermisoBusiness business = new UsuarioPermisoBusinessImpl(daoFactory);
            var domains = business.findByUsuario(idUsuario);
            return UsuarioPermisoDTOAssembler.getUsuarioPermisoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando permisos por usuario: " + exception.getMessage(),
                    "Error al buscar permisos",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UsuarioPermisoDTO findById(UUID id) {
        try {
            UsuarioPermisoBusiness business = new UsuarioPermisoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return UsuarioPermisoDTOAssembler.getUsuarioPermisoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando permiso de usuario: " + exception.getMessage(),
                    "Error al buscar permiso de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(UsuarioPermisoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioPermisoDTOAssembler.getUsuarioPermisoDTOAssembler().toDomain(dto);
            UsuarioPermisoBusiness business = new UsuarioPermisoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando permiso de usuario: " + exception.getMessage(),
                    "Error al actualizar permiso de usuario",
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

            UsuarioPermisoBusiness business = new UsuarioPermisoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando permiso de usuario: " + exception.getMessage(),
                    "Error al eliminar permiso de usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}

