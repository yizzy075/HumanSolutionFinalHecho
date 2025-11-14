package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class UsuarioDocumentoDomain extends Domain {

    private UUID idUsuario;
    private UUID idTipoDocumento;
    private LocalDate fecha;
    private UUID idEstadoSolicitud;

    private UsuarioDocumentoDomain(UUID id, UUID idUsuario, UUID idTipoDocumento, LocalDate fecha, UUID idEstadoSolicitud) {
        super(id);
        setIdUsuario(idUsuario);
        setIdTipoDocumento(idTipoDocumento);
        setFecha(fecha);
        setIdEstadoSolicitud(idEstadoSolicitud);
    }

    public static UsuarioDocumentoDomain create(UUID id, UUID idUsuario, UUID idTipoDocumento, LocalDate fecha, UUID idEstadoSolicitud) {
        return new UsuarioDocumentoDomain(id, idUsuario, idTipoDocumento, fecha, idEstadoSolicitud);
    }

    public static UsuarioDocumentoDomain create() {
        return new UsuarioDocumentoDomain(UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), LocalDate.now(), UUIDHelper.getDefaultUUID());
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

    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }

    public void validarIdTipoDocumento() {
        if (UUIDHelper.isDefault(idTipoDocumento)) {
            throw new IllegalArgumentException("El tipo de documento es obligatorio");
        }
    }

    public void validarFecha() {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
    }

    public void validarIdEstadoSolicitud() {
        if (UUIDHelper.isDefault(idEstadoSolicitud)) {
            throw new IllegalArgumentException("El estado de solicitud es obligatorio");
        }
    }

    public void validar() {
        validarIdUsuario();
        validarIdTipoDocumento();
        validarFecha();
        validarIdEstadoSolicitud();
    }
}