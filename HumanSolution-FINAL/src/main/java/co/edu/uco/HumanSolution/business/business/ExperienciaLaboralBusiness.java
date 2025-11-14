package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.ExperienciaLaboralDomain;

import java.util.List;
import java.util.UUID;

public interface ExperienciaLaboralBusiness {

    void create(ExperienciaLaboralDomain domain);

    List<ExperienciaLaboralDomain> list();

    List<ExperienciaLaboralDomain> findByUsuario(UUID idUsuario);

    ExperienciaLaboralDomain findById(UUID id);

    void update(ExperienciaLaboralDomain domain);

    void delete(UUID id);
}