package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.TipoPermisoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.TipoPermisoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.TipoPermisoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.TipoPermisoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.TipoPermisoDTO;

import java.util.List;
import java.util.UUID;

public final class TipoPermisoFacadeImpl implements TipoPermisoFacade {

    private DAOFactory daoFactory;

    public TipoPermisoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(TipoPermisoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = TipoPermisoDTOAssembler.getTipoPermisoDTOAssembler().toDomain(dto);
            TipoPermisoBusiness business = new TipoPermisoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando tipo de permiso: " + exception.getMessage(),
                    "Error al crear tipo de permiso",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<TipoPermisoDTO> list() {
        try {
            TipoPermisoBusiness business = new TipoPermisoBusinessImpl(daoFactory);
            var domains = business.list();
            return TipoPermisoDTOAssembler.getTipoPermisoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando tipos de permiso: " + exception.getMessage(),
                    "Error al listar tipos de permiso",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public TipoPermisoDTO findById(UUID id) {
        try {
            TipoPermisoBusiness business = new TipoPermisoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return TipoPermisoDTOAssembler.getTipoPermisoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando tipo de permiso: " + exception.getMessage(),
                    "Error al buscar tipo de permiso",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(TipoPermisoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = TipoPermisoDTOAssembler.getTipoPermisoDTOAssembler().toDomain(dto);
            TipoPermisoBusiness business = new TipoPermisoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando tipo de permiso: " + exception.getMessage(),
                    "Error al actualizar tipo de permiso",
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

            TipoPermisoBusiness business = new TipoPermisoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando tipo de permiso: " + exception.getMessage(),
                    "Error al eliminar tipo de permiso",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}