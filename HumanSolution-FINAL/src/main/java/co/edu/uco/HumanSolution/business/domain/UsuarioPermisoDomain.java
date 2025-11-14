package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class UsuarioPermisoDomain extends Domain {

    private UUID idUsuario;
    private UUID idTipoPermiso;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private UUID idEstadoSolicitud;

    private UsuarioPermisoDomain(UUID id, UUID idUsuario, UUID idTipoPermiso, LocalDate fechaInicio, LocalDate fechaFin, UUID idEstadoSolicitud) {
        super(id);
        setIdUsuario(idUsuario);
        setIdTipoPermiso(idTipoPermiso);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setIdEstadoSolicitud(idEstadoSolicitud);
    }

    public static UsuarioPermisoDomain create(UUID id, UUID idUsuario, UUID idTipoPermiso, LocalDate fechaInicio, LocalDate fechaFin, UUID idEstadoSolicitud) {
        return new UsuarioPermisoDomain(id, idUsuario, idTipoPermiso, fechaInicio, fechaFin, idEstadoSolicitud);
    }

    public static UsuarioPermisoDomain create() {
        return new UsuarioPermisoDomain(UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), LocalDate.now(), LocalDate.now(), UUIDHelper.getDefaultUUID());
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdTipoPermiso() {
        return idTipoPermiso;
    }

    private void setIdTipoPermiso(UUID idTipoPermiso) {
        this.idTipoPermiso = UUIDHelper.getDefault(idTipoPermiso, UUIDHelper.getDefaultUUID());
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
        this.fechaFin = fechaFin != null ? fechaFin : LocalDate.now();
    }

    public UUID getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    private void setIdEstadoSolicitud(UUID idEstadoSolicitud) {
        this.idEstadoSolicitud = UUIDHelper.getDefault(idEstadoSolicitud, UUIDHelper.getDefaultUUID());
    }

    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }

    public void validarIdTipoPermiso() {
        if (UUIDHelper.isDefault(idTipoPermiso)) {
            throw new IllegalArgumentException("El tipo de permiso es obligatorio");
        }
    }

    public void validarFechaInicio() {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        }
    }

    public void validarFechaFin() {
        if (fechaFin == null) {
            throw new IllegalArgumentException("La fecha de fin es obligatoria");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

    public void validarIdEstadoSolicitud() {
        if (UUIDHelper.isDefault(idEstadoSolicitud)) {
            throw new IllegalArgumentException("El estado de solicitud es obligatorio");
        }
    }

    public void validar() {
        validarIdUsuario();
        validarIdTipoPermiso();
        validarFechaInicio();
        validarFechaFin();
        validarIdEstadoSolicitud();
    }
}