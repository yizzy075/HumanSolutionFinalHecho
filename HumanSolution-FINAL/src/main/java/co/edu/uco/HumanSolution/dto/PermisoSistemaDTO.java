package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class PermisoSistemaDTO {

    private String id;
    private String nombre;

    public PermisoSistemaDTO(String id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public PermisoSistemaDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
    }

    public static PermisoSistemaDTO create(String id, String nombre) {
        return new PermisoSistemaDTO(id, nombre);
    }

    public static PermisoSistemaDTO create() {
        return new PermisoSistemaDTO();
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
}