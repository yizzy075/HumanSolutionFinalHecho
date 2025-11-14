package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.ExperienciaLaboralDTO;

import java.util.List;
import java.util.UUID;

public interface ExperienciaLaboralFacade {

    void create(ExperienciaLaboralDTO dto);

    List<ExperienciaLaboralDTO> list();

    List<ExperienciaLaboralDTO> findByUsuario(UUID idUsuario);

    ExperienciaLaboralDTO findById(UUID id);

    void update(ExperienciaLaboralDTO dto);

    void delete(UUID id);
}