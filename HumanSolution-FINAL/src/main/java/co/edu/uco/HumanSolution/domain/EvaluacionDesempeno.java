package co.edu.uco.HumanSolution.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entidad de dominio que representa una evaluación de desempeño.
 * Contiene todos los campos según el esquema de base de datos.
 */
public class EvaluacionDesempeno {

    private UUID id;
    private UUID idUsuario;
    private UUID idEvaluador;
    private UUID idContrato;
    private LocalDate fecha;
    private Integer calificacion;
    private String observacion;
    private String criterios;

    // Constructor vacío
    public EvaluacionDesempeno() {
    }

    // Constructor completo
    public EvaluacionDesempeno(UUID id, UUID idUsuario, UUID idEvaluador, UUID idContrato,
                               LocalDate fecha, Integer calificacion, String observacion, String criterios) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEvaluador = idEvaluador;
        this.idContrato = idContrato;
        this.fecha = fecha;
        this.calificacion = calificacion;
        this.observacion = observacion;
        this.criterios = criterios;
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UUID getIdEvaluador() {
        return idEvaluador;
    }

    public void setIdEvaluador(UUID idEvaluador) {
        this.idEvaluador = idEvaluador;
    }

    public UUID getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(UUID idContrato) {
        this.idContrato = idContrato;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCriterios() {
        return criterios;
    }

    public void setCriterios(String criterios) {
        this.criterios = criterios;
    }

    @Override
    public String toString() {
        return "EvaluacionDesempeno{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idEvaluador=" + idEvaluador +
                ", idContrato=" + idContrato +
                ", fecha=" + fecha +
                ", calificacion=" + calificacion +
                ", observacion='" + observacion + '\'' +
                ", criterios='" + criterios + '\'' +
                '}';
    }
}
