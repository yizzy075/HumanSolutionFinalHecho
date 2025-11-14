package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@jakarta.persistence.Entity
@Table(name = "evaluacion_desempeno")
public class EvaluacionDesempenoEntity extends Entity {

    @Column(name = "id_usuario", nullable = false)
    private UUID idUsuario;

    @Column(name = "id_evaluador", nullable = false)
    private UUID idEvaluador;

    @Column(name = "id_contrato", nullable = false)
    private UUID idContrato;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "observacion", nullable = false, length = 500)
    private String observacion;

    @Column(name = "criterios", nullable = false, length = 1000)
    private String criterios;

    public EvaluacionDesempenoEntity(UUID id, UUID idUsuario, UUID idEvaluador, UUID idContrato, LocalDate fecha, Integer calificacion, String observacion, String criterios) {
        super(id);
        setIdUsuario(idUsuario);
        setIdEvaluador(idEvaluador);
        setIdContrato(idContrato);
        setFecha(fecha);
        setCalificacion(calificacion);
        setObservacion(observacion);
        setCriterios(criterios);
    }

    public EvaluacionDesempenoEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setIdEvaluador(UUIDHelper.getDefaultUUID());
        setIdContrato(UUIDHelper.getDefaultUUID());
        setFecha(LocalDate.now());
        setCalificacion(0);
        setObservacion(TextHelper.EMPTY);
        setCriterios(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "";
    }

    public static EvaluacionDesempenoEntity create(UUID id, UUID idUsuario, UUID idEvaluador, UUID idContrato, LocalDate fecha, Integer calificacion, String observacion, String criterios) {
        return new EvaluacionDesempenoEntity(id, idUsuario, idEvaluador, idContrato, fecha, calificacion, observacion, criterios);
    }

    public static EvaluacionDesempenoEntity create() {
        return new EvaluacionDesempenoEntity();
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
}
