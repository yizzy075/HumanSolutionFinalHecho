package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class RolPermisoDomain extends Domain {

    private UUID idRol;
    private UUID idPermiso;

    private RolPermisoDomain(UUID id, UUID idRol, UUID idPermiso) {
        super(id);
        setIdRol(idRol);
        setIdPermiso(idPermiso);
    }

    public static RolPermisoDomain create(UUID id, UUID idRol, UUID idPermiso) {
        return new RolPermisoDomain(id, idRol, idPermiso);
    }

    public static RolPermisoDomain create() {
        return new RolPermisoDomain(UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID());
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

    public void validarIdRol() {
        if (UUIDHelper.isDefault(idRol)) {
            throw new IllegalArgumentException("El ID del rol es obligatorio");
        }
    }

    public void validarIdPermiso() {
        if (UUIDHelper.isDefault(idPermiso)) {
            throw new IllegalArgumentException("El ID del permiso es obligatorio");
        }
    }

    public void validar() {
        validarIdRol();
        validarIdPermiso();
    }
}