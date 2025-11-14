package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class PuestoDomain extends Domain {

    private String nombre;
    private UUID idUsuario;
    private UUID idUnidad;
    private UUID idEstado;
    private UUID idJefe;

    private PuestoDomain(UUID id, String nombre, UUID idUsuario, UUID idUnidad, UUID idEstado, UUID idJefe) {
        super(id);
        setNombre(nombre);
        setIdUsuario(idUsuario);
        setIdUnidad(idUnidad);
        setIdEstado(idEstado);
        setIdJefe(idJefe);
    }

    public static PuestoDomain create(UUID id, String nombre, UUID idUsuario, UUID idUnidad, UUID idEstado, UUID idJefe) {
        return new PuestoDomain(id, nombre, idUsuario, idUnidad, idEstado, idJefe);
    }

    public static PuestoDomain create() {
        return new PuestoDomain(UUIDHelper.getDefaultUUID(), TextHelper.EMPTY, UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID());
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdUnidad() {
        return idUnidad;
    }

    private void setIdUnidad(UUID idUnidad) {
        this.idUnidad = UUIDHelper.getDefault(idUnidad, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdEstado() {
        return idEstado;
    }

    private void setIdEstado(UUID idEstado) {
        this.idEstado = UUIDHelper.getDefault(idEstado, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdJefe() {
        return idJefe;
    }

    private void setIdJefe(UUID idJefe) {
        this.idJefe = UUIDHelper.getDefault(idJefe, UUIDHelper.getDefaultUUID());
    }

    public void validarNombre() {
        if (TextHelper.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre del puesto es obligatorio");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder 50 caracteres");
        }
    }

    public void validarIdUnidad() {
        if (UUIDHelper.isDefault(idUnidad)) {
            throw new IllegalArgumentException("La unidad organizativa es obligatoria");
        }
    }

    public void validarIdEstado() {
        if (UUIDHelper.isDefault(idEstado)) {
            throw new IllegalArgumentException("El estado del puesto es obligatorio");
        }
    }

    public void validar() {
        validarNombre();
        validarIdUnidad();
        validarIdEstado();
    }
}