package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;

public class UsuarioHoraExtraDTO {

    private String id;
    private String idUsuario;
    private LocalDate fecha;
    private String idEstadoSolicitud;
    private int horas;
    private String idTipoHoraExtra;

    public UsuarioHoraExtraDTO(String id, String idUsuario, LocalDate fecha, String idEstadoSolicitud, int horas, String idTipoHoraExtra) {
        setId(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setIdEstadoSolicitud(idEstadoSolicitud);
        setHoras(horas);
        setIdTipoHoraExtra(idTipoHoraExtra);
    }

    public UsuarioHoraExtraDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setIdUsuario(UUIDHelper.getDefaultUUIDAsString());
        setFecha(LocalDate.now());
        setIdEstadoSolicitud(UUIDHelper.getDefaultUUIDAsString());
        setHoras(0);
        setIdTipoHoraExtra(UUIDHelper.getDefaultUUIDAsString());
    }

    public static UsuarioHoraExtraDTO create(String id, String idUsuario, LocalDate fecha, String idEstadoSolicitud, int horas, String idTipoHoraExtra) {
        return new UsuarioHoraExtraDTO(id, idUsuario, fecha, idEstadoSolicitud, horas, idTipoHoraExtra);
    }

    public static UsuarioHoraExtraDTO create() {
        return new UsuarioHoraExtraDTO();
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(String idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getIdTipoHoraExtra() {
        return idTipoHoraExtra;
    }

    public void setIdTipoHoraExtra(String idTipoHoraExtra) {
        this.idTipoHoraExtra = idTipoHoraExtra;
    }
}