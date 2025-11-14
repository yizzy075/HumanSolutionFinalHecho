package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class EstadoSolicitudDTO {

    private String id;
    private String nombre;

    public EstadoSolicitudDTO(String id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public EstadoSolicitudDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
    }

    public static EstadoSolicitudDTO create(String id, String nombre) {
        return new EstadoSolicitudDTO(id, nombre);
    }

    public static EstadoSolicitudDTO create() {
        return new EstadoSolicitudDTO();
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