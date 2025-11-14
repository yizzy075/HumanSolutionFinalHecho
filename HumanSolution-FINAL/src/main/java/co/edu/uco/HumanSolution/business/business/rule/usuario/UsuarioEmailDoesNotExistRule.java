package co.edu.uco.HumanSolution.business.business.rule.usuario;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.StringHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;

public class UsuarioEmailDoesNotExistRule implements Rule<UsuarioDomain> {

    private DAOFactory daoFactory;

    public UsuarioEmailDoesNotExistRule(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void validate(UsuarioDomain data) {
        if (!StringHelper.isEmpty(data.getCorreo())) {
            var filterEntity = co.edu.uco.HumanSolution.entity.UsuarioEntity.create(
                    co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper.getDefaultUUID(),
                    "",
                    "",
                    data.getCorreo(),
                    "",
                    ""
            );
            var entity = daoFactory.getUsuarioDAO().read(filterEntity);

            if (!entity.isEmpty()) {
                var existingUser = entity.get(0);
                if (!existingUser.getId().equals(data.getId())) {
                    throw new BusinessException(
                            "Ya existe un usuario con el correo " + data.getCorreo(),
                            "El correo ya está registrado en el sistema"
                    );
                }
            }
        }
    }
}

