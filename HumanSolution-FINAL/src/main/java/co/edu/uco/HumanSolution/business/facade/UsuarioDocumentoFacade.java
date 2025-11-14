package co.edu.uco.HumanSolution.business.facade;

import co.edu.uco.HumanSolution.dto.UsuarioDocumentoDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioDocumentoFacade {

    void create(UsuarioDocumentoDTO dto);

    List<UsuarioDocumentoDTO> list();

    List<UsuarioDocumentoDTO> findByUsuario(UUID idUsuario);

    UsuarioDocumentoDTO findById(UUID id);

    void update(UsuarioDocumentoDTO dto);

    void delete(UUID id);
}