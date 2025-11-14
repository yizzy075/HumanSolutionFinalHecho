package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class RolPermisoEntity extends Entity {

    private UUID idRol;
    private UUID idPermiso;

    public RolPermisoEntity(UUID id, UUID idRol, UUID idPermiso) {
        super(id);
        setIdRol(idRol);
        setIdPermiso(idPermiso);
    }

    public RolPermisoEntity() {
        super();
        setIdRol(UUIDHelper.getDefaultUUID());
        setIdPermiso(UUIDHelper.getDefaultUUID());
    }

    @Override
    public String name() {
        return "";
    }

    public static RolPermisoEntity create(UUID id, UUID idRol, UUID idPermiso) {
        return new RolPermisoEntity(id, idRol, idPermiso);
    }

    public static RolPermisoEntity create() {
        return new RolPermisoEntity();
    }

    public UUID getIdRol() {
        return idRol;
    }

    private void setIdRol(UUID idRol) {
        this.idRol = UUIDHelper.getDefault(idRol, UUIDHelper.getDefaultUUID());
    }

    public UUID getIdPermiso() {
        return idPermiso;
    }

    private void setIdPermiso(UUID idPermiso) {
        this.idPermiso = UUIDHelper.getDefault(idPermiso, UUIDHelper.getDefaultUUID());
    }
}