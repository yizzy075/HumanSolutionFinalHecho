package co.edu.uco.HumanSolution.business.business.validator.usuario;

import co.edu.uco.HumanSolution.business.business.rule.generics.StringLengthValueIsValidRule;
import co.edu.uco.HumanSolution.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.HumanSolution.business.business.rule.usuario.UsuarioEmailDoesNotExistRule;
import co.edu.uco.HumanSolution.business.business.validator.Validator;
import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.crosscutting.helper.StringHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;

public class ValidateUsuarioForRegister implements Validator<UsuarioDomain> {

    private DAOFactory daoFactory;

    public ValidateUsuarioForRegister(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void validate(UsuarioDomain data) {
        // Validar que el nombre esté presente y tenga longitud válida
        new StringValueIsPresentRule("nombre", "El nombre del usuario es obligatorio")
                .validate(data.getNombre());
        
        new StringLengthValueIsValidRule("nombre", 3, 50, 
                "El nombre debe tener entre 3 y 50 caracteres")
                .validate(data.getNombre());

        // Validar que el correo esté presente y tenga formato válido
        new StringValueIsPresentRule("correo", "El correo del usuario es obligatorio")
                .validate(data.getCorreo());

        if (!StringHelper.isEmpty(data.getCorreo()) && !StringHelper.isEmailFormat(data.getCorreo())) {
            throw new co.edu.uco.HumanSolution.crosscutting.exception.BusinessException(
                    "El formato del correo no es válido",
                    "El correo debe tener un formato válido (ejemplo: usuario@dominio.com)"
            );
        }

        // Validar que el correo no exista
        new UsuarioEmailDoesNotExistRule(daoFactory).validate(data);
    }
}

