package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class PuestoEntity extends Entity {

    private String nombre;
    private UUID idUsuario;
    private UUID idUnidad;
    private UUID idEstado;
    private UUID idJefe;

    public PuestoEntity(UUID id, String nombre, UUID idUsuario, UUID idUnidad, UUID idEstado, UUID idJefe) {
        super(id);
        setNombre(nombre);
        setIdUsuario(idUsuario);
        setIdUnidad(idUnidad);
        setIdEstado(idEstado);
        setIdJefe(idJefe);
    }

    public PuestoEntity() {
        super();
        setNombre(TextHelper.EMPTY);
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setIdUnidad(UUIDHelper.getDefaultUUID());
        setIdEstado(UUIDHelper.getDefaultUUID());
        setIdJefe(UUIDHelper.getDefaultUUID());
    }

    @Override
    public String name() {
        return "";
    }

    public static PuestoEntity create(UUID id, String nombre, UUID idUsuario, UUID idUnidad, UUID idEstado, UUID idJefe) {
        return new PuestoEntity(id, nombre, idUsuario, idUnidad, idEstado, idJefe);
    }

    public static PuestoEntity create() {
        return new PuestoEntity();
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
}