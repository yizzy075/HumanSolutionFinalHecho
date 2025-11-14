package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.ExperienciaLaboralEntity;
import java.util.List;
import java.util.UUID;

public interface ExperienciaLaboralDAO {

    void create(ExperienciaLaboralEntity entity);

    List<ExperienciaLaboralEntity> read(ExperienciaLaboralEntity entity);

    void update(ExperienciaLaboralEntity entity);

    void delete(UUID id);
}