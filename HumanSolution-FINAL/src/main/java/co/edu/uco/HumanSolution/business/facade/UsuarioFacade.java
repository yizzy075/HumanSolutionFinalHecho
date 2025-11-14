package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.UsuarioDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioFacade {

    void register(UsuarioDTO dto);

    List<UsuarioDTO> list();

    UsuarioDTO findById(UUID id);

    UsuarioDTO findByEmail(String email);

    void update(UsuarioDTO dto);

    void delete(UUID id);
}