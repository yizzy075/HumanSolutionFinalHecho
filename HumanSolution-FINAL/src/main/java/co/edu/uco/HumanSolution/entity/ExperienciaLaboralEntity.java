package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class ExperienciaLaboralEntity extends Entity {

    private UUID idUsuario;
    private String empresa;
    private String cargo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ExperienciaLaboralEntity(UUID id, UUID idUsuario, String empresa, String cargo, LocalDate fechaInicio, LocalDate fechaFin) {
        super(id);
        setIdUsuario(idUsuario);
        setEmpresa(empresa);
        setCargo(cargo);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
    }

    public ExperienciaLaboralEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setEmpresa(TextHelper.EMPTY);
        setCargo(TextHelper.EMPTY);
        setFechaInicio(LocalDate.now());
        setFechaFin(null);
    }

    @Override
    public String name() {
        return "";
    }

    public static ExperienciaLaboralEntity create(UUID id, UUID idUsuario, String empresa, String cargo, LocalDate fechaInicio, LocalDate fechaFin) {
        return new ExperienciaLaboralEntity(id, idUsuario, empresa, cargo, fechaInicio, fechaFin);
    }

    public static ExperienciaLaboralEntity create() {
        return new ExperienciaLaboralEntity();
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public String getEmpresa() {
        return empresa;
    }

    private void setEmpresa(String empresa) {
        this.empresa = TextHelper.applyTrim(empresa);
    }

    public String getCargo() {
        return cargo;
    }

    private void setCargo(String cargo) {
        this.cargo = TextHelper.applyTrim(cargo);
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio != null ? fechaInicio : LocalDate.now();
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}