package co.edu.uco.HumanSolution.business.business.rule.evaluaciondesempeno;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;

/**
 * ED-05: La calificación debe estar en un rango válido (1-10)
 */
public class EvaluacionCalificacionRangeIsValidRule implements Rule<EvaluacionDesempenoDomain> {

    private static final EvaluacionCalificacionRangeIsValidRule INSTANCE = new EvaluacionCalificacionRangeIsValidRule();
    private static final int MIN_CALIFICACION = 1;
    private static final int MAX_CALIFICACION = 10;

    private EvaluacionCalificacionRangeIsValidRule() {
    }

    public static EvaluacionCalificacionRangeIsValidRule getInstance() {
        return INSTANCE;
    }

    @Override
    public void validate(EvaluacionDesempenoDomain data) {
        Integer calificacion = data.getCalificacion();

        if (calificacion == null) {
            throw new BusinessException(
                    "La calificación es obligatoria",
                    "ED-05: La calificación debe estar presente"
            );
        }

        if (calificacion < MIN_CALIFICACION || calificacion > MAX_CALIFICACION) {
            throw new BusinessException(
                    String.format("La calificación debe estar entre %d y %d. Valor proporcionado: %d",
                            MIN_CALIFICACION, MAX_CALIFICACION, calificacion),
                    String.format("ED-05: Rango de calificación inválido (%d-%d)", MIN_CALIFICACION, MAX_CALIFICACION)
            );
        }
    }
}
