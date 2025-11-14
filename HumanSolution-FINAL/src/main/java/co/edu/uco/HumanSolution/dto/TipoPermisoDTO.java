package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class TipoPermisoDTO {

    private String id;
    private String nombre;
    private String descripcion;

    public TipoPermisoDTO(String id, String nombre, String descripcion) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public TipoPermisoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
        setDescripcion(TextHelper.EMPTY);
    }

    public static TipoPermisoDTO create(String id, String nombre, String descripcion) {
        return new TipoPermisoDTO(id, nombre, descripcion);
    }

    public static TipoPermisoDTO create() {
        return new TipoPermisoDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = TextHelper.getDefault(id, UUIDHelper.getDefaultUUIDAsString());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
    }
}