package co.edu.uco.HumanSolution.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Entidad de dominio que representa un contrato.
 */
public class Contrato {

    private UUID id;
    private UUID idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipo;
    private boolean vigente;

    // Constructor vacío
    public Contrato() {
    }

    // Constructor completo
    public Contrato(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin,
                    String tipo, boolean vigente) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipo = tipo;
        this.vigente = vigente;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", tipo='" + tipo + '\'' +
                ", vigente=" + vigente +
                '}';
    }
}
