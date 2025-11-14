package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class UsuarioPermisoEntity extends Entity {

    private UUID idUsuario;
    private UUID idTipoPermiso;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private UUID idEstadoSolicitud;

    public UsuarioPermisoEntity(UUID id, UUID idUsuario, UUID idTipoPermiso, LocalDate fechaInicio, LocalDate fechaFin, UUID idEstadoSolicitud) {
        super(id);
        setIdUsuario(idUsuario);
        setIdTipoPermiso(idTipoPermiso);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setIdEstadoSolicitud(idEstadoSolicitud);
    }

    public UsuarioPermisoEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setIdTipoPermiso(UUIDHelper.getDefaultUUID());
        setFechaInicio(LocalDate.now());
        setFechaFin(LocalDate.now());
        setIdEstadoSolicitud(UUIDHelper.getDefaultUUID());
    }

    @Override
    public String name() {
        return "";
    }

    public static UsuarioPermisoEntity create(UUID id, UUID idUsuario, UUID idTipoPermiso, LocalDate fechaInicio, LocalDate fechaFin, UUID idEstadoSolicitud) {
        return new UsuarioPermisoEntity(id, idUsuario, idTipoPermiso, fechaInicio, fechaFin, idEstadoSolicitud);
    }

    public static UsuarioPermisoEntity create() {
        return new UsuarioPermisoEntity();
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
}