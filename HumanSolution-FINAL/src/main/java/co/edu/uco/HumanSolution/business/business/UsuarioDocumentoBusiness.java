package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.UsuarioDocumentoDomain;

import java.util.List;
import java.util.UUID;

public interface UsuarioDocumentoBusiness {

    void create(UsuarioDocumentoDomain domain);

    List<UsuarioDocumentoDomain> list();

    List<UsuarioDocumentoDomain> findByUsuario(UUID idUsuario);

    UsuarioDocumentoDomain findById(UUID id);

    void update(UsuarioDocumentoDomain domain);

    void delete(UUID id);
}