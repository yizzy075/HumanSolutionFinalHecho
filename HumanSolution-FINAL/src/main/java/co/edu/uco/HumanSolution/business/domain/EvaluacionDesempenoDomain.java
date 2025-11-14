package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class EvaluacionDesempenoDomain extends Domain {

    private UUID idUsuario;
    private UUID idEvaluador;
    private UUID idContrato;
    private LocalDate fecha;
    private Integer calificacion;
    private String observacion;
    private String criterios;

    private EvaluacionDesempenoDomain(UUID id, UUID idUsuario, UUID idEvaluador, UUID idContrato, LocalDate fecha, Integer calificacion, String observacion, String criterios) {
        super(id);
        setIdUsuario(idUsuario);
        setIdEvaluador(idEvaluador);
        setIdContrato(idContrato);
        setFecha(fecha);
        setCalificacion(calificacion);
        setObservacion(observacion);
        setCriterios(criterios);
    }

    public static EvaluacionDesempenoDomain create(UUID id, UUID idUsuario, UUID idEvaluador, UUID idContrato, LocalDate fecha, Integer calificacion, String observacion, String criterios) {
        return new EvaluacionDesempenoDomain(id, idUsuario, idEvaluador, idContrato, fecha, calificacion, observacion, criterios);
    }

    public static EvaluacionDesempenoDomain create() {
        return new EvaluacionDesempenoDomain(
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                LocalDate.now(),
                0,
                TextHelper.EMPTY,
                TextHelper.EMPTY
        );
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdEvaluador() {
        return idEvaluador;
    }

    private void setIdEvaluador(UUID idEvaluador) {
        this.idEvaluador = UUIDHelper.getDefault(idEvaluador, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdContrato() {
        return idContrato;
    }

    private void setIdContrato(UUID idContrato) {
        this.idContrato = UUIDHelper.getDefault(idContrato, UUIDHelper.getDefaultUUID());
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(LocalDate fecha) {
        this.fecha = fecha != null ? fecha : LocalDate.now();
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    private void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion != null ? calificacion : 0;
    }

    public String getObservacion() {
        return observacion;
    }

    private void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }

    public String getCriterios() {
        return criterios;
    }

    private void setCriterios(String criterios) {
        this.criterios = TextHelper.applyTrim(criterios);
    }

    // Validaciones básicas del dominio
    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio");
        }
    }

    public void validarIdEvaluador() {
        if (UUIDHelper.isDefault(idEvaluador)) {
            throw new IllegalArgumentException("El ID del evaluador es obligatorio");
        }
    }

    public void validarIdContrato() {
        if (UUIDHelper.isDefault(idContrato)) {
            throw new IllegalArgumentException("El ID del contrato es obligatorio");
        }
    }

    public void validarFecha() {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
    }

    public void validarCalificacion() {
        if (calificacion == null) {
            throw new IllegalArgumentException("La calificación es obligatoria");
        }
        if (calificacion < 1 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 10");
        }
    }

    public void validarObservacion() {
        if (TextHelper.isEmpty(observacion)) {
            throw new IllegalArgumentException("La observación es obligatoria");
        }
        if (observacion.length() < 5) {
            throw new IllegalArgumentException("La observación debe tener al menos 5 caracteres");
        }
        if (observacion.length() > 500) {
            throw new IllegalArgumentException("La observación no puede exceder 500 caracteres");
        }
    }

    public void validarCriterios() {
        if (TextHelper.isEmpty(criterios)) {
            throw new IllegalArgumentException("Los criterios son obligatorios");
        }
        if (criterios.length() < 10) {
            throw new IllegalArgumentException("Los criterios deben tener al menos 10 caracteres");
        }
        if (criterios.length() > 1000) {
            throw new IllegalArgumentException("Los criterios no pueden exceder 1000 caracteres");
        }
    }

    public void validar() {
        validarIdUsuario();
        validarIdEvaluador();
        validarIdContrato();
        validarFecha();
        validarCalificacion();
        validarObservacion();
        validarCriterios();
    }
}
