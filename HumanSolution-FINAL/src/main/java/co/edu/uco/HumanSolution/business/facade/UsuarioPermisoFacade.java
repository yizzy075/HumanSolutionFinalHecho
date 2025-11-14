package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.UsuarioPermisoDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioPermisoFacade {

    void create(UsuarioPermisoDTO dto);

    List<UsuarioPermisoDTO> list();

    List<UsuarioPermisoDTO> findByUsuario(UUID idUsuario);

    UsuarioPermisoDTO findById(UUID id);

    void update(UsuarioPermisoDTO dto);

    void delete(UUID id);
}