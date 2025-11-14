package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class UsuarioHoraExtraDomain extends Domain {

    private UUID idUsuario;
    private LocalDate fecha;
    private UUID idEstadoSolicitud;
    private int horas;
    private UUID idTipoHoraExtra;

    private UsuarioHoraExtraDomain(UUID id, UUID idUsuario, LocalDate fecha, UUID idEstadoSolicitud, int horas, UUID idTipoHoraExtra) {
        super(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setIdEstadoSolicitud(idEstadoSolicitud);
        setHoras(horas);
        setIdTipoHoraExtra(idTipoHoraExtra);
    }

    public static UsuarioHoraExtraDomain create(UUID id, UUID idUsuario, LocalDate fecha, UUID idEstadoSolicitud, int horas, UUID idTipoHoraExtra) {
        return new UsuarioHoraExtraDomain(id, idUsuario, fecha, idEstadoSolicitud, horas, idTipoHoraExtra);
    }

    public static UsuarioHoraExtraDomain create() {
        return new UsuarioHoraExtraDomain(UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), LocalDate.now(), UUIDHelper.getDefaultUUID(), 0, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
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

    public int getHoras() {
        return horas;
    }

    private void setHoras(int horas) {
        this.horas = horas;
    }

    public UUID getIdTipoHoraExtra() {
        return idTipoHoraExtra;
    }

    private void setIdTipoHoraExtra(UUID idTipoHoraExtra) {
        this.idTipoHoraExtra = UUIDHelper.getDefault(idTipoHoraExtra, UUIDHelper.getDefaultUUID());
    }

    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }

    public void validarFecha() {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura");
        }
    }

    public void validarIdEstadoSolicitud() {
        if (UUIDHelper.isDefault(idEstadoSolicitud)) {
            throw new IllegalArgumentException("El estado de solicitud es obligatorio");
        }
    }

    public void validarHoras() {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas deben ser mayores a cero");
        }
        if (horas > 12) {
            throw new IllegalArgumentException("No se pueden registrar más de 12 horas extra por día");
        }
    }

    public void validarIdTipoHoraExtra() {
        if (UUIDHelper.isDefault(idTipoHoraExtra)) {
            throw new IllegalArgumentException("El tipo de hora extra es obligatorio");
        }
    }

    public void validar() {
        validarIdUsuario();
        validarFecha();
        validarIdEstadoSolicitud();
        validarHoras();
        validarIdTipoHoraExtra();
    }
}