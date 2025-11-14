package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.RolPermisoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.RolPermisoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.RolPermisoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.RolPermisoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.RolPermisoDTO;

import java.util.List;
import java.util.UUID;

public final class RolPermisoFacadeImpl implements RolPermisoFacade {

    private DAOFactory daoFactory;

    public RolPermisoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(RolPermisoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = RolPermisoDTOAssembler.getRolPermisoDTOAssembler().toDomain(dto);
            RolPermisoBusiness business = new RolPermisoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade asignando permiso a rol: " + exception.getMessage(),
                    "Error al asignar permiso",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<RolPermisoDTO> list() {
        try {
            RolPermisoBusiness business = new RolPermisoBusinessImpl(daoFactory);
            var domains = business.list();
            return RolPermisoDTOAssembler.getRolPermisoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando permisos de roles: " + exception.getMessage(),
                    "Error al listar permisos de roles",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<RolPermisoDTO> findByRol(UUID idRol) {
        try {
            RolPermisoBusiness business = new RolPermisoBusinessImpl(daoFactory);
            var domains = business.findByRol(idRol);
            return RolPermisoDTOAssembler.getRolPermisoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando permisos por rol: " + exception.getMessage(),
                    "Error al buscar permisos del rol",
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

            RolPermisoBusiness business = new RolPermisoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando permiso de rol: " + exception.getMessage(),
                    "Error al eliminar permiso de rol",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}