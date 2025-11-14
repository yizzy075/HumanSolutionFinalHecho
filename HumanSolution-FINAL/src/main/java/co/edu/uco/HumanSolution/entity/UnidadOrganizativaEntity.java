package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class UnidadOrganizativaEntity extends Entity {

    private String nombre;
    private UUID idUnidadSuperior;

    public UnidadOrganizativaEntity(UUID id, String nombre, UUID idUnidadSuperior) {
        super(id);
        setNombre(nombre);
        setIdUnidadSuperior(idUnidadSuperior);
    }

    public UnidadOrganizativaEntity() {
        super();
        setNombre(TextHelper.EMPTY);
        setIdUnidadSuperior(UUIDHelper.getDefaultUUID());
    }

    @Override
    public String name() {
        return "";
    }

    public static UnidadOrganizativaEntity create(UUID id, String nombre, UUID idUnidadSuperior) {
        return new UnidadOrganizativaEntity(id, nombre, idUnidadSuperior);
    }

    public static UnidadOrganizativaEntity create() {
        return new UnidadOrganizativaEntity();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public UUID getIdUnidadSuperior() {
        return idUnidadSuperior;
    }

    private void setIdUnidadSuperior(UUID idUnidadSuperior) {
        this.idUnidadSuperior = UUIDHelper.getDefault(idUnidadSuperior, UUIDHelper.getDefaultUUID());
    }
}