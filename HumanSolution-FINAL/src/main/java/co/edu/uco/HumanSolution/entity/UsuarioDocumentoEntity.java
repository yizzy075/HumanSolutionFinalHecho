package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class UsuarioDocumentoEntity extends Entity {

    private UUID idUsuario;
    private UUID idTipoDocumento;
    private LocalDate fecha;
    private UUID idEstadoSolicitud;

    public UsuarioDocumentoEntity(UUID id, UUID idUsuario, UUID idTipoDocumento, LocalDate fecha, UUID idEstadoSolicitud) {
        super(id);
        setIdUsuario(idUsuario);
        setIdTipoDocumento(idTipoDocumento);
        setFecha(fecha);
        setIdEstadoSolicitud(idEstadoSolicitud);
    }

    public UsuarioDocumentoEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setIdTipoDocumento(UUIDHelper.getDefaultUUID());
        setFecha(LocalDate.now());
        setIdEstadoSolicitud(UUIDHelper.getDefaultUUID());
    }

    @Override
    public String name() {
        return "";
    }

    public static UsuarioDocumentoEntity create(UUID id, UUID idUsuario, UUID idTipoDocumento, LocalDate fecha, UUID idEstadoSolicitud) {
        return new UsuarioDocumentoEntity(id, idUsuario, idTipoDocumento, fecha, idEstadoSolicitud);
    }

    public static UsuarioDocumentoEntity create() {
        return new UsuarioDocumentoEntity();
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdTipoDocumento() {
        return idTipoDocumento;
    }

    private void setIdTipoDocumento(UUID idTipoDocumento) {
        this.idTipoDocumento = UUIDHelper.getDefault(idTipoDocumento, UUIDHelper.getDefaultUUID());
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(LocalDate fecha) {
        this.fecha = fecha != null ? fecha : LocalDate.now();
    }

    public UUID getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    private void setIdEstadoSolicitud(UUID idEstadoSolicitud) {
        this.idEstadoSolicitud = UUIDHelper.getDefault(idEstadoSolicitud, UUIDHelper.getDefaultUUID());
    }
}