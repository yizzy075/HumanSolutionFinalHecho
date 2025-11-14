package co.edu.uco.HumanSolution.business.business;

import co.edu.uco.HumanSolution.business.domain.UsuarioHoraExtraDomain;

import java.util.List;
import java.util.UUID;

public interface UsuarioHoraExtraBusiness {

    void create(UsuarioHoraExtraDomain domain);

    List<UsuarioHoraExtraDomain> list();

    List<UsuarioHoraExtraDomain> findByUsuario(UUID idUsuario);

    UsuarioHoraExtraDomain findById(UUID id);

    void update(UsuarioHoraExtraDomain domain);

    void delete(UUID id);
}