package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;

public class UsuarioDocumentoDTO {

    private String id;
    private String idUsuario;
    private String idTipoDocumento;
    private LocalDate fecha;
    private String idEstadoSolicitud;

    public UsuarioDocumentoDTO(String id, String idUsuario, String idTipoDocumento, LocalDate fecha, String idEstadoSolicitud) {
        setId(id);
        setIdUsuario(idUsuario);
        setIdTipoDocumento(idTipoDocumento);
        setFecha(fecha);
        setIdEstadoSolicitud(idEstadoSolicitud);
    }

    public UsuarioDocumentoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setIdUsuario(UUIDHelper.getDefaultUUIDAsString());
        setIdTipoDocumento(UUIDHelper.getDefaultUUIDAsString());
        setFecha(LocalDate.now());
        setIdEstadoSolicitud(UUIDHelper.getDefaultUUIDAsString());
    }

    public static UsuarioDocumentoDTO create(String id, String idUsuario, String idTipoDocumento, LocalDate fecha, String idEstadoSolicitud) {
        return new UsuarioDocumentoDTO(id, idUsuario, idTipoDocumento, fecha, idEstadoSolicitud);
    }

    public static UsuarioDocumentoDTO create() {
        return new UsuarioDocumentoDTO();
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

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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
}