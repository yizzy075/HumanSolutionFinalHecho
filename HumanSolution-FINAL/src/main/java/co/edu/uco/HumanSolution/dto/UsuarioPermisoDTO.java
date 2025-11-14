package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;

public class UsuarioPermisoDTO {

    private String id;
    private String idUsuario;
    private String idTipoPermiso;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String idEstadoSolicitud;

    public UsuarioPermisoDTO(String id, String idUsuario, String idTipoPermiso, LocalDate fechaInicio, LocalDate fechaFin, String idEstadoSolicitud) {
        setId(id);
        setIdUsuario(idUsuario);
        setIdTipoPermiso(idTipoPermiso);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setIdEstadoSolicitud(idEstadoSolicitud);
    }

    public UsuarioPermisoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setIdUsuario(UUIDHelper.getDefaultUUIDAsString());
        setIdTipoPermiso(UUIDHelper.getDefaultUUIDAsString());
        setFechaInicio(LocalDate.now());
        setFechaFin(LocalDate.now());
        setIdEstadoSolicitud(UUIDHelper.getDefaultUUIDAsString());
    }

    public static UsuarioPermisoDTO create(String id, String idUsuario, String idTipoPermiso, LocalDate fechaInicio, LocalDate fechaFin, String idEstadoSolicitud) {
        return new UsuarioPermisoDTO(id, idUsuario, idTipoPermiso, fechaInicio, fechaFin, idEstadoSolicitud);
    }

    public static UsuarioPermisoDTO create() {
        return new UsuarioPermisoDTO();
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

    public String getIdTipoPermiso() {
        return idTipoPermiso;
    }

    public void setIdTipoPermiso(String idTipoPermiso) {
        this.idTipoPermiso = idTipoPermiso;
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

    public String getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(String idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }
}