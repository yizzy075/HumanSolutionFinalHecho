package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class UsuarioHoraExtraEntity extends Entity {

    private UUID idUsuario;
    private LocalDate fecha;
    private UUID idEstadoSolicitud;
    private int horas;
    private UUID idTipoHoraExtra;

    public UsuarioHoraExtraEntity(UUID id, UUID idUsuario, LocalDate fecha, UUID idEstadoSolicitud, int horas, UUID idTipoHoraExtra) {
        super(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setIdEstadoSolicitud(idEstadoSolicitud);
        setHoras(horas);
        setIdTipoHoraExtra(idTipoHoraExtra);
    }

    public UsuarioHoraExtraEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setFecha(LocalDate.now());
        setIdEstadoSolicitud(UUIDHelper.getDefaultUUID());
        setHoras(0);
        setIdTipoHoraExtra(UUIDHelper.getDefaultUUID());
    }

    @Override
    public String name() {
        return "";
    }

    public static UsuarioHoraExtraEntity create(UUID id, UUID idUsuario, LocalDate fecha, UUID idEstadoSolicitud, int horas, UUID idTipoHoraExtra) {
        return new UsuarioHoraExtraEntity(id, idUsuario, fecha, idEstadoSolicitud, horas, idTipoHoraExtra);
    }

    public static UsuarioHoraExtraEntity create() {
        return new UsuarioHoraExtraEntity();
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
        this.horas = Math.max(0, horas);
    }

    public UUID getIdTipoHoraExtra() {
        return idTipoHoraExtra;
    }

    private void setIdTipoHoraExtra(UUID idTipoHoraExtra) {
        this.idTipoHoraExtra = UUIDHelper.getDefault(idTipoHoraExtra, UUIDHelper.getDefaultUUID());
    }
}