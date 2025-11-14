package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import java.util.UUID;

public class EstadoSolicitudEntity extends Entity {

    private String nombre;

    public EstadoSolicitudEntity(UUID id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public EstadoSolicitudEntity() {
        super();
        setNombre(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "";
    }

    public static EstadoSolicitudEntity create(UUID id, String nombre) {
        return new EstadoSolicitudEntity(id, nombre);
    }

    public static EstadoSolicitudEntity create() {
        return new EstadoSolicitudEntity();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}