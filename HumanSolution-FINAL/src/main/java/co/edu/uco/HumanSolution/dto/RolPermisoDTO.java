package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class RolPermisoDTO {

    private String id;
    private String idRol;
    private String idPermiso;

    public RolPermisoDTO(String id, String idRol, String idPermiso) {
        setId(id);
        setIdRol(idRol);
        setIdPermiso(idPermiso);
    }

    public RolPermisoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setIdRol(UUIDHelper.getDefaultUUIDAsString());
        setIdPermiso(UUIDHelper.getDefaultUUIDAsString());
    }

    public static RolPermisoDTO create(String id, String idRol, String idPermiso) {
        return new RolPermisoDTO(id, idRol, idPermiso);
    }

    public static RolPermisoDTO create() {
        return new RolPermisoDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(String idPermiso) {
        this.idPermiso = idPermiso;
    }
}