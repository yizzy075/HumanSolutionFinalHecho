package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.ExperienciaLaboralDTOAssembler;
import co.edu.uco.HumanSolution.business.business.ExperienciaLaboralBusiness;
import co.edu.uco.HumanSolution.business.business.impl.ExperienciaLaboralBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.ExperienciaLaboralFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.ExperienciaLaboralDTO;

import java.util.List;
import java.util.UUID;

public final class ExperienciaLaboralFacadeImpl implements ExperienciaLaboralFacade {

    private DAOFactory daoFactory;

    public ExperienciaLaboralFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(ExperienciaLaboralDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = ExperienciaLaboralDTOAssembler.getExperienciaLaboralDTOAssembler().toDomain(dto);
            ExperienciaLaboralBusiness business = new ExperienciaLaboralBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando experiencia laboral: " + exception.getMessage(),
                    "Error al crear experiencia laboral",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<ExperienciaLaboralDTO> list() {
        try {
            ExperienciaLaboralBusiness business = new ExperienciaLaboralBusinessImpl(daoFactory);
            var domains = business.list();
            return ExperienciaLaboralDTOAssembler.getExperienciaLaboralDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando experiencias laborales: " + exception.getMessage(),
                    "Error al listar experiencias laborales",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<ExperienciaLaboralDTO> findByUsuario(UUID idUsuario) {
        try {
            ExperienciaLaboralBusiness business = new ExperienciaLaboralBusinessImpl(daoFactory);
            var domains = business.findByUsuario(idUsuario);
            return ExperienciaLaboralDTOAssembler.getExperienciaLaboralDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando experiencias laborales por usuario: " + exception.getMessage(),
                    "Error al buscar experiencias laborales",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public ExperienciaLaboralDTO findById(UUID id) {
        try {
            ExperienciaLaboralBusiness business = new ExperienciaLaboralBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return ExperienciaLaboralDTOAssembler.getExperienciaLaboralDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando experiencia laboral: " + exception.getMessage(),
                    "Error al buscar experiencia laboral",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(ExperienciaLaboralDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = ExperienciaLaboralDTOAssembler.getExperienciaLaboralDTOAssembler().toDomain(dto);
            ExperienciaLaboralBusiness business = new ExperienciaLaboralBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando experiencia laboral: " + exception.getMessage(),
                    "Error al actualizar experiencia laboral",
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

            ExperienciaLaboralBusiness business = new ExperienciaLaboralBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando experiencia laboral: " + exception.getMessage(),
                    "Error al eliminar experiencia laboral",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}

