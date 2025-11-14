package co.edu.uco.HumanSolution.business.business.rule.evaluaciondesempeno;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.ContratoDAO;
import co.edu.uco.HumanSolution.entity.ContratoEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * ED-04: La evaluación debe estar vinculada a un contrato vigente del evaluado
 */
public class EvaluacionLinkedToActiveContractRule implements Rule<EvaluacionDesempenoDomain> {

    private ContratoDAO contratoDAO;

    public EvaluacionLinkedToActiveContractRule(ContratoDAO contratoDAO) {
        this.contratoDAO = contratoDAO;
    }

    @Override
    public void validate(EvaluacionDesempenoDomain data) {
        // Validar que el ID del contrato no sea el valor por defecto
        if (UUIDHelper.isDefault(data.getIdContrato())) {
            throw new BusinessException(
                    "La evaluación debe estar vinculada a un contrato",
                    "ED-04: Debe especificar un contrato vigente"
            );
        }

        // Buscar el contrato por ID
        ContratoEntity contratoFilter = ContratoEntity.create(
                data.getIdContrato(),
                UUIDHelper.getDefaultUUID(),
                null,
                null,
                null
        );

        List<ContratoEntity> contratos = contratoDAO.read(contratoFilter);

        if (contratos.isEmpty()) {
            throw new BusinessException(
                    String.format("No existe el contrato con ID %s", data.getIdContrato()),
                    "ED-04: El contrato especificado no existe"
            );
        }

        ContratoEntity contrato = contratos.get(0);

        // Verificar que el contrato pertenece al usuario evaluado
        if (!contrato.getIdUsuario().equals(data.getIdUsuario())) {
            throw new BusinessException(
                    String.format("El contrato %s no pertenece al usuario %s",
                            data.getIdContrato(), data.getIdUsuario()),
                    "ED-04: El contrato no pertenece al usuario evaluado"
            );
        }

        // Verificar que el contrato esté vigente en la fecha de evaluación
        LocalDate fechaEvaluacion = data.getFecha();
        LocalDate fechaInicio = contrato.getFechaInicio();
        LocalDate fechaFin = contrato.getFechaFin();

        if (fechaEvaluacion.isBefore(fechaInicio)) {
            throw new BusinessException(
                    String.format("El contrato no estaba vigente en la fecha de evaluación %s (inicia el %s)",
                            fechaEvaluacion, fechaInicio),
                    "ED-04: El contrato debe estar vigente en la fecha de evaluación"
            );
        }

        if (fechaFin != null && fechaEvaluacion.isAfter(fechaFin)) {
            throw new BusinessException(
                    String.format("El contrato ya no está vigente en la fecha de evaluación %s (finalizó el %s)",
                            fechaEvaluacion, fechaFin),
                    "ED-04: El contrato debe estar vigente en la fecha de evaluación"
            );
        }
    }
}
