package co.edu.uco.HumanSolution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContratoDTO {

    private String id;
    private String idUsuario;
    private String fechaInicio;
    private String fechaFin;
    private String sueldo;

    public ContratoDTO() {
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("idUsuario")
    public String getIdUsuario() {
        return idUsuario;
    }

    @JsonProperty("idUsuario")
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @JsonProperty("fechaInicio")
    public String getFechaInicio() {
        return fechaInicio;
    }

    @JsonProperty("fechaInicio")
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @JsonProperty("fechaFin")
    public String getFechaFin() {
        return fechaFin;
    }

    @JsonProperty("fechaFin")
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @JsonProperty("sueldo")
    public String getSueldo() {
        return sueldo;
    }

    @JsonProperty("sueldo")
    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "ContratoDTO{" +
                "id='" + id + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", sueldo='" + sueldo + '\'' +
                '}';
    }
}