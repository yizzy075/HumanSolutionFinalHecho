package co.edu.uco.HumanSolution.business.business.rule.usuario;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;

import java.util.UUID;

public class UsuarioExistsByIdRule implements Rule<UUID> {

    private DAOFactory daoFactory;

    public UsuarioExistsByIdRule(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void validate(UUID data) {
        if (!UUIDHelper.isDefault(data)) {
            var filterEntity = co.edu.uco.HumanSolution.entity.UsuarioEntity.create(
                    data,
                    "",
                    "",
                    "",
                    "",
                    ""  // ✅ CAMBIAR de UUID a String vacío
            );
            var entity = daoFactory.getUsuarioDAO().read(filterEntity);

            if (entity.isEmpty()) {
                throw new BusinessException(
                        "El usuario con ID " + data + " no existe",
                        "Usuario no encontrado"
                );
            }
        }
    }
}