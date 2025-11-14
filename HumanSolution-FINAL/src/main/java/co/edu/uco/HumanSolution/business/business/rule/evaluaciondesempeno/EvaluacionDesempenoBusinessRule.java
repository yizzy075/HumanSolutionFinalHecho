package co.edu.uco.HumanSolution.business.business.rule.evaluaciondesempeno;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.data.dao.EvaluacionDesempenoDAO;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;
import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

/**
 * Reglas de negocio para la gestión de Evaluaciones de Desempeño
 */
public class EvaluacionDesempenoBusinessRule {

    private final EvaluacionDesempenoDAO evaluacionDAO;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public EvaluacionDesempenoBusinessRule(EvaluacionDesempenoDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }

    private UUID parseUUID(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new HumanSolutionException(
                    "PARSE-ERROR: El ID proporcionado no es válido: " + id,
                    "Verificar formato del ID"
            );
        }
    }

    private LocalDate parseDate(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(fecha, DATE_FORMATTER);
        } catch (Exception e) {
            throw new HumanSolutionException(
                    "PARSE-ERROR: La fecha proporcionada no es válida: " + fecha,
                    "Usar formato yyyy-MM-dd"
            );
        }
    }

    public void validateMandatoryFields(EvaluacionDesempenoDTO evaluacion) {
        if (evaluacion.getUsuario() == null || TextHelper.isEmpty(evaluacion.getUsuario().getId())) {
            throw new HumanSolutionException(
                    "ED-01: El usuario (evaluado) es obligatorio para registrar la evaluación",
                    "Debe proporcionar un usuario válido"
            );
        }

        if (TextHelper.isEmpty(evaluacion.getFecha())) {
            throw new HumanSolutionException(
                    "ED-01: La fecha es obligatoria para registrar la evaluación",
                    "Debe proporcionar una fecha válida"
            );
        }

        if (TextHelper.isEmpty(evaluacion.getObservacion())) {
            throw new HumanSolutionException(
                    "ED-01: Las observaciones son obligatorias para registrar la evaluación",
                    "Debe proporcionar observaciones"
            );
        }

        if (evaluacion.getObservacion().length() < 5 || evaluacion.getObservacion().length() > 500) {
            throw new HumanSolutionException(
                    "ED-05: Las observaciones deben tener entre 5 y 500 caracteres",
                    "Ajuste la longitud de las observaciones"
            );
        }
    }

    public void validateUniqueUserDateCombination(UUID usuarioId, LocalDate fecha, UUID evaluacionId) {
        if (evaluacionId == null) {
            boolean exists = evaluacionDAO.existsByUsuarioIdAndFecha(usuarioId, fecha);
            if (exists) {
                throw new HumanSolutionException(
                        "ED-02: Ya existe una evaluación registrada para este usuario en la fecha " + fecha,
                        "No se pueden registrar evaluaciones duplicadas para el mismo usuario en la misma fecha"
                );
            }
        } else {
            Optional<EvaluacionDesempenoEntity> existingEvaluacion =
                    evaluacionDAO.findByUsuarioIdAndFecha(usuarioId, fecha);

            if (existingEvaluacion.isPresent() && !existingEvaluacion.get().getId().equals(evaluacionId)) {
                throw new HumanSolutionException(
                        "ED-02: Ya existe otra evaluación para este usuario en la fecha " + fecha,
                        "No se pueden registrar evaluaciones duplicadas para el mismo usuario en la misma fecha"
                );
            }
        }
    }

    public void validateFechaNotFuture(LocalDate fecha) {
        if (fecha.isAfter(LocalDate.now())) {
            throw new HumanSolutionException(
                    "ED-03: La fecha de evaluación no puede ser futura. Fecha proporcionada: " + fecha,
                    "La fecha de evaluación debe ser actual o pasada"
            );
        }
    }

    public void validateCalificacionRange(int calificacion) {
        if (calificacion < 1 || calificacion > 10) {
            throw new HumanSolutionException(
                    "ED-05: La calificación debe estar entre 1 y 10. Valor proporcionado: " + calificacion,
                    "La calificación debe estar en el rango de 1 a 10"
            );
        }
    }

    public void validateForCreate(EvaluacionDesempenoDTO evaluacion) {
        validateMandatoryFields(evaluacion);

        LocalDate fecha = parseDate(evaluacion.getFecha());
        validateFechaNotFuture(fecha);
        validateCalificacionRange(evaluacion.getCalificacion());

        UUID usuarioId = parseUUID(evaluacion.getUsuario().getId());
        validateUniqueUserDateCombination(usuarioId, fecha, null);
    }

    public void validateForUpdate(EvaluacionDesempenoDTO evaluacion) {
        validateMandatoryFields(evaluacion);

        LocalDate fecha = parseDate(evaluacion.getFecha());
        validateFechaNotFuture(fecha);
        validateCalificacionRange(evaluacion.getCalificacion());

        UUID usuarioId = parseUUID(evaluacion.getUsuario().getId());
        UUID evaluacionId = parseUUID(evaluacion.getId());
        validateUniqueUserDateCombination(usuarioId, fecha, evaluacionId);
    }
}