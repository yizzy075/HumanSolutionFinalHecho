package co.edu.uco.HumanSolution.business.business.rule.evaluaciondesempeno;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;

import java.time.LocalDate;

/**
 * ED-03: La fecha de evaluación no puede ser futura
 */
public class EvaluacionFechaNotInFutureRule implements Rule<EvaluacionDesempenoDomain> {

    private static final EvaluacionFechaNotInFutureRule INSTANCE = new EvaluacionFechaNotInFutureRule();

    private EvaluacionFechaNotInFutureRule() {
    }

    public static EvaluacionFechaNotInFutureRule getInstance() {
        return INSTANCE;
    }

    @Override
    public void validate(EvaluacionDesempenoDomain data) {
        if (data.getFecha() != null && data.getFecha().isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha de evaluación no puede ser futura. Fecha proporcionada: " + data.getFecha(),
                    "ED-03: La fecha de evaluación debe ser actual o pasada"
            );
        }
    }
}
