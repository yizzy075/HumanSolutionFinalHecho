package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import java.util.UUID;

public class EstadoPuestoEntity extends Entity {

    private String nombre;

    public EstadoPuestoEntity(UUID id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public EstadoPuestoEntity() {
        super();
        setNombre(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "";
    }

    public static EstadoPuestoEntity create(UUID id, String nombre) {
        return new EstadoPuestoEntity(id, nombre);
    }

    public static EstadoPuestoEntity create() {
        return new EstadoPuestoEntity();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}