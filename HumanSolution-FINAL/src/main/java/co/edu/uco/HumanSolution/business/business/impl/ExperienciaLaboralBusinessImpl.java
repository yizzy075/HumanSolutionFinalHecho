package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.ExperienciaLaboralEntityAssembler;
import co.edu.uco.HumanSolution.business.business.ExperienciaLaboralBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.ExperienciaLaboralDomain;
import co.edu.uco.HumanSolution.entity.ExperienciaLaboralEntity;

import java.util.List;
import java.util.UUID;

public final class ExperienciaLaboralBusinessImpl implements ExperienciaLaboralBusiness {

    private DAOFactory daoFactory;

    public ExperienciaLaboralBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(ExperienciaLaboralDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = ExperienciaLaboralDomain.create(
                    id,
                    domain.getIdUsuario(),
                    domain.getEmpresa(),
                    domain.getCargo(),
                    domain.getFechaInicio(),
                    domain.getFechaFin()
            );

            var entity = ExperienciaLaboralEntityAssembler.getExperienciaLaboralEntityAssembler().toEntity(domainWithId);
            daoFactory.getExperienciaLaboralDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando experiencia laboral: " + exception.getMessage(),
                    "Error inesperado al crear experiencia laboral",
                    exception  // ✅ Aquí SÍ se pasa la Exception
            );
        }
    }

    @Override
    public List<ExperienciaLaboralDomain> list() {
        try {
            ExperienciaLaboralEntity filter = ExperienciaLaboralEntity.create();
            List<ExperienciaLaboralEntity> entities = daoFactory.getExperienciaLaboralDAO().read(filter);
            return ExperienciaLaboralEntityAssembler.getExperienciaLaboralEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando experiencias laborales: " + exception.getMessage(),
                    "Error inesperado al listar experiencias laborales",
                    exception  // ✅ Exception como tercer parámetro
            );
        }
    }

    @Override
    public List<ExperienciaLaboralDomain> findByUsuario(UUID idUsuario) {
        try {
            ExperienciaLaboralEntity filter = ExperienciaLaboralEntity.create(
                    UUIDHelper.getDefaultUUID(),
                    idUsuario,
                    "",
                    "",
                    null,
                    null
            );
            List<ExperienciaLaboralEntity> entities = daoFactory.getExperienciaLaboralDAO().read(filter);
            return ExperienciaLaboralEntityAssembler.getExperienciaLaboralEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando experiencias por usuario: " + exception.getMessage(),
                    "Error inesperado al buscar experiencias",
                    exception  // ✅ Exception como tercer parámetro
            );
        }
    }

    @Override
    public ExperienciaLaboralDomain findById(UUID id) {
        try {
            ExperienciaLaboralEntity filter = ExperienciaLaboralEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    "",
                    "",
                    null,
                    null
            );
            List<ExperienciaLaboralEntity> entities = daoFactory.getExperienciaLaboralDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Experiencia laboral con ID " + id + " no existe",
                        "No se encontró la experiencia laboral"  // ✅ Solo 2 parámetros
                );
            }

            return ExperienciaLaboralEntityAssembler.getExperienciaLaboralEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando experiencia laboral: " + exception.getMessage(),
                    "Error inesperado al buscar experiencia laboral",
                    exception  // ✅ Exception como tercer parámetro
            );
        }
    }

    @Override
    public void update(ExperienciaLaboralDomain domain) {
        try {
            domain.validar();

            var entity = ExperienciaLaboralEntityAssembler.getExperienciaLaboralEntityAssembler().toEntity(domain);
            daoFactory.getExperienciaLaboralDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando experiencia laboral: " + exception.getMessage(),
                    "Error inesperado al actualizar experiencia laboral",
                    exception  // ✅ Exception como tercer parámetro
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getExperienciaLaboralDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando experiencia laboral: " + exception.getMessage(),
                    "Error inesperado al eliminar experiencia laboral",
                    exception  // ✅ Exception como tercer parámetro
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = ExperienciaLaboralEntity.create(
                id,
                UUIDHelper.getDefaultUUID(),
                "",
                "",
                null,
                null
        );
        var existing = daoFactory.getExperienciaLaboralDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = ExperienciaLaboralEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    "",
                    "",
                    null,
                    null
            );
            existing = daoFactory.getExperienciaLaboralDAO().read(entity);
        }

        return id;
    }
}