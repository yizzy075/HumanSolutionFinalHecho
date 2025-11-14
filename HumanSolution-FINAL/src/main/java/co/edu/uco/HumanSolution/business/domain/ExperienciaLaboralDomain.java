package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class ExperienciaLaboralDomain extends Domain {

    private UUID idUsuario;
    private String empresa;
    private String cargo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private ExperienciaLaboralDomain(UUID id, UUID idUsuario, String empresa, String cargo, LocalDate fechaInicio, LocalDate fechaFin) {
        super(id);
        setIdUsuario(idUsuario);
        setEmpresa(empresa);
        setCargo(cargo);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
    }

    public static ExperienciaLaboralDomain create(UUID id, UUID idUsuario, String empresa, String cargo, LocalDate fechaInicio, LocalDate fechaFin) {
        return new ExperienciaLaboralDomain(id, idUsuario, empresa, cargo, fechaInicio, fechaFin);
    }

    public static ExperienciaLaboralDomain create() {
        return new ExperienciaLaboralDomain(UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), TextHelper.EMPTY, TextHelper.EMPTY, LocalDate.now(), null);
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

    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }

    public void validarEmpresa() {
        if (TextHelper.isEmpty(empresa)) {
            throw new IllegalArgumentException("El nombre de la empresa es obligatorio");
        }
        if (empresa.length() > 50) {
            throw new IllegalArgumentException("El nombre de la empresa no puede exceder 50 caracteres");
        }
    }

    public void validarCargo() {
        if (TextHelper.isEmpty(cargo)) {
            throw new IllegalArgumentException("El cargo es obligatorio");
        }
        if (cargo.length() > 200) {
            throw new IllegalArgumentException("El cargo no puede exceder 200 caracteres");
        }
    }

    public void validarFechaInicio() {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        }
    }

    public void validarFechaFin() {
        if (fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

    public void validar() {
        validarIdUsuario();
        validarEmpresa();
        validarCargo();
        validarFechaInicio();
        validarFechaFin();
    }
}