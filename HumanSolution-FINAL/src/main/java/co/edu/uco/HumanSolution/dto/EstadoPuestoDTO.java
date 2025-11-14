package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class EstadoPuestoDTO {

    private String id;
    private String nombre;

    public EstadoPuestoDTO(String id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public EstadoPuestoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
    }

    public static EstadoPuestoDTO create(String id, String nombre) {
        return new EstadoPuestoDTO(id, nombre);
    }

    public static EstadoPuestoDTO create() {
        return new EstadoPuestoDTO();
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