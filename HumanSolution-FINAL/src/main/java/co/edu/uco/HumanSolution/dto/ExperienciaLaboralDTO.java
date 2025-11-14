package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;

public class ExperienciaLaboralDTO {

    private String id;
    private String idUsuario;
    private String empresa;
    private String cargo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ExperienciaLaboralDTO(String id, String idUsuario, String empresa, String cargo, LocalDate fechaInicio, LocalDate fechaFin) {
        setId(id);
        setIdUsuario(idUsuario);
        setEmpresa(empresa);
        setCargo(cargo);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
    }

    public ExperienciaLaboralDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setIdUsuario(UUIDHelper.getDefaultUUIDAsString());
        setEmpresa(TextHelper.EMPTY);
        setCargo(TextHelper.EMPTY);
        setFechaInicio(LocalDate.now());
        setFechaFin(null);
    }

    public static ExperienciaLaboralDTO create(String id, String idUsuario, String empresa, String cargo, LocalDate fechaInicio, LocalDate fechaFin) {
        return new ExperienciaLaboralDTO(id, idUsuario, empresa, cargo, fechaInicio, fechaFin);
    }

    public static ExperienciaLaboralDTO create() {
        return new ExperienciaLaboralDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = TextHelper.applyTrim(empresa);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = TextHelper.applyTrim(cargo);
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
}