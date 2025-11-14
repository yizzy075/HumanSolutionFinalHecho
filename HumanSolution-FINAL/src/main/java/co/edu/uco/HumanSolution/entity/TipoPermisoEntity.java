package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import java.util.UUID;

public class TipoPermisoEntity extends Entity {

    private String nombre;
    private String descripcion;

    public TipoPermisoEntity(UUID id, String nombre, String descripcion) {
        super(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public TipoPermisoEntity() {
        super();
        setNombre(TextHelper.EMPTY);
        setDescripcion(TextHelper.EMPTY);
    }

    @Override
    public String name() {
        return "";
    }

    public static TipoPermisoEntity create(UUID id, String nombre, String descripcion) {
        return new TipoPermisoEntity(id, nombre, descripcion);
    }

    public static TipoPermisoEntity create() {
        return new TipoPermisoEntity();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
    }
}