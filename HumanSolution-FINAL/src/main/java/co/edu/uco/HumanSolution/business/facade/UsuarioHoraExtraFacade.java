package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.UsuarioHoraExtraDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioHoraExtraFacade {

    void create(UsuarioHoraExtraDTO dto);

    List<UsuarioHoraExtraDTO> list();

    List<UsuarioHoraExtraDTO> findByUsuario(UUID idUsuario);

    UsuarioHoraExtraDTO findById(UUID id);

    void update(UsuarioHoraExtraDTO dto);

    void delete(UUID id);
}