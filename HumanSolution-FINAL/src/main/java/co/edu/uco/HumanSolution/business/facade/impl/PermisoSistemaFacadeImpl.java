package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.PermisoSistemaDTOAssembler;
import co.edu.uco.HumanSolution.business.business.PermisoSistemaBusiness;
import co.edu.uco.HumanSolution.business.business.impl.PermisoSistemaBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.PermisoSistemaFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.PermisoSistemaDTO;

import java.util.List;
import java.util.UUID;

public final class PermisoSistemaFacadeImpl implements PermisoSistemaFacade {

    private DAOFactory daoFactory;

    public PermisoSistemaFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(PermisoSistemaDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = PermisoSistemaDTOAssembler.getPermisoSistemaDTOAssembler().toDomain(dto);
            PermisoSistemaBusiness business = new PermisoSistemaBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando permiso de sistema: " + exception.getMessage(),
                    "Error al crear permiso de sistema",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<PermisoSistemaDTO> list() {
        try {
            PermisoSistemaBusiness business = new PermisoSistemaBusinessImpl(daoFactory);
            var domains = business.list();
            return PermisoSistemaDTOAssembler.getPermisoSistemaDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando permisos de sistema: " + exception.getMessage(),
                    "Error al listar permisos de sistema",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public PermisoSistemaDTO findById(UUID id) {
        try {
            PermisoSistemaBusiness business = new PermisoSistemaBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return PermisoSistemaDTOAssembler.getPermisoSistemaDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando permiso de sistema: " + exception.getMessage(),
                    "Error al buscar permiso de sistema",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(PermisoSistemaDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = PermisoSistemaDTOAssembler.getPermisoSistemaDTOAssembler().toDomain(dto);
            PermisoSistemaBusiness business = new PermisoSistemaBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando permiso de sistema: " + exception.getMessage(),
                    "Error al actualizar permiso de sistema",
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

            PermisoSistemaBusiness business = new PermisoSistemaBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando permiso de sistema: " + exception.getMessage(),
                    "Error al eliminar permiso de sistema",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}