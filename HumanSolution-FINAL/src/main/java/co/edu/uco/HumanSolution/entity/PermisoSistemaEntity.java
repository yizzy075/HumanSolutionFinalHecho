package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import java.util.UUID;

public class PermisoSistemaEntity extends Entity {

    private String nombre;

    public PermisoSistemaEntity(UUID id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public PermisoSistemaEntity() {
        super();
        setNombre(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "";
    }

    public static PermisoSistemaEntity create(UUID id, String nombre) {
        return new PermisoSistemaEntity(id, nombre);
    }

    public static PermisoSistemaEntity create() {
        return new PermisoSistemaEntity();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}