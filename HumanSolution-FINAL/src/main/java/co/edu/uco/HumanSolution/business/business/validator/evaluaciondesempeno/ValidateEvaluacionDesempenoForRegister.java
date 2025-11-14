package co.edu.uco.HumanSolution.business.business.validator.evaluaciondesempeno;

import co.edu.uco.HumanSolution.business.business.rule.evaluaciondesempeno.*;
import co.edu.uco.HumanSolution.business.business.rule.generics.*;
import co.edu.uco.HumanSolution.business.business.rule.usuario.UsuarioExistsByIdRule;
import co.edu.uco.HumanSolution.business.business.validator.Validator;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;

/**
 * Validador completo para registro de evaluaciones de desempeño
 * Implementa todas las reglas de negocio ED-01 a ED-05
 */
public class ValidateEvaluacionDesempenoForRegister implements Validator<EvaluacionDesempenoDomain> {

    private DAOFactory daoFactory;

    public ValidateEvaluacionDesempenoForRegister(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void validate(EvaluacionDesempenoDomain data) {
        validateBasicFields(data);
        validateBusinessRules(data);
    }

    private void validateBasicFields(EvaluacionDesempenoDomain data) {
        // ED-01 y ED-05: Validar que los IDs no sean valores por defecto
        var idUsuarioRule = new IdValueIsNotDefaultValueRule("ID Usuario", "El usuario es obligatorio");
        idUsuarioRule.validate(data.getIdUsuario());

        var idEvaluadorRule = new IdValueIsNotDefaultValueRule("ID Evaluador", "El evaluador es obligatorio");
        idEvaluadorRule.validate(data.getIdEvaluador());

        var idContratoRule = new IdValueIsNotDefaultValueRule("ID Contrato", "El contrato es obligatorio");
        idContratoRule.validate(data.getIdContrato());

        // ED-01 y ED-05: Validar que la fecha esté presente
        var fechaRule = new DateValueIsPresentRule("Fecha de evaluación", "La fecha es obligatoria");
        fechaRule.validate(data.getFecha());

        // ED-05: Validar rango de calificación (1-10)
        var calificacionRangeRule = EvaluacionCalificacionRangeIsValidRule.getInstance();
        calificacionRangeRule.validate(data);

        // ED-01 y ED-05: Validar observación (longitud y formato)
        var observacionPresentRule = new StringValueIsPresentRule("Observación", "La observación es obligatoria");
        observacionPresentRule.validate(data.getObservacion());

        var observacionLengthRule = new StringLengthValueIsValidRule("Observación", 5, 500, "Longitud de observación inválida");
        observacionLengthRule.validate(data.getObservacion());

        // ED-01 y ED-05: Validar criterios (longitud y formato)
        var criteriosPresentRule = new StringValueIsPresentRule("Criterios", "Los criterios son obligatorios");
        criteriosPresentRule.validate(data.getCriterios());

        var criteriosLengthRule = new StringLengthValueIsValidRule("Criterios", 10, 1000, "Longitud de criterios inválida");
        criteriosLengthRule.validate(data.getCriterios());
    }

    private void validateBusinessRules(EvaluacionDesempenoDomain data) {
        // Validar que el usuario evaluado exista
        var usuarioExistsRule = new UsuarioExistsByIdRule(daoFactory);  // ✅ CORRECTO: Pasar daoFactory, no el DAO
        usuarioExistsRule.validate(data.getIdUsuario());

        // Validar que el evaluador exista
        var evaluadorExistsRule = new UsuarioExistsByIdRule(daoFactory);  // ✅ CORRECTO: Pasar daoFactory, no el DAO
        evaluadorExistsRule.validate(data.getIdEvaluador());

        // ED-02: Validar que no exista una evaluación duplicada para el mismo usuario en la misma fecha
        var noDuplicateRule = new EvaluacionNotDuplicatedForSameDateRule(daoFactory.getEvaluacionDesempenoDAO());
        noDuplicateRule.validate(data);

        // ED-03: Validar que la fecha no sea futura
        var fechaNotFutureRule = EvaluacionFechaNotInFutureRule.getInstance();
        fechaNotFutureRule.validate(data);

        // ED-04: Validar que la evaluación esté vinculada a un contrato vigente
        var activeContractRule = new EvaluacionLinkedToActiveContractRule(daoFactory.getContratoDAO());
        activeContractRule.validate(data);
    }
}