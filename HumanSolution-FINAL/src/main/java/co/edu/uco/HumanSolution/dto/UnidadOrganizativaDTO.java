package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class UnidadOrganizativaDTO {

    private String id;
    private String nombre;
    private String idUnidadSuperior;

    public UnidadOrganizativaDTO(String id, String nombre, String idUnidadSuperior) {
        setId(id);
        setNombre(nombre);
        setIdUnidadSuperior(idUnidadSuperior);
    }

    public UnidadOrganizativaDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
        setIdUnidadSuperior(UUIDHelper.getDefaultUUIDAsString());
    }

    public static UnidadOrganizativaDTO create(String id, String nombre, String idUnidadSuperior) {
        return new UnidadOrganizativaDTO(id, nombre, idUnidadSuperior);
    }

    public static UnidadOrganizativaDTO create() {
        return new UnidadOrganizativaDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getIdUnidadSuperior() {
        return idUnidadSuperior;
    }

    public void setIdUnidadSuperior(String idUnidadSuperior) {
        this.idUnidadSuperior = idUnidadSuperior;
    }
}