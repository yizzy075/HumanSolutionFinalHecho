package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class UnidadOrganizativaDomain extends Domain {

    private String nombre;
    private UUID idUnidadSuperior;

    private UnidadOrganizativaDomain(UUID id, String nombre, UUID idUnidadSuperior) {
        super(id);
        setNombre(nombre);
        setIdUnidadSuperior(idUnidadSuperior);
    }

    public static UnidadOrganizativaDomain create(UUID id, String nombre, UUID idUnidadSuperior) {
        return new UnidadOrganizativaDomain(id, nombre, idUnidadSuperior);
    }

    public static UnidadOrganizativaDomain create() {
        return new UnidadOrganizativaDomain(UUIDHelper.getDefaultUUID(), TextHelper.EMPTY, UUIDHelper.getDefaultUUID());
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

    public void validarNombre() {
        if (TextHelper.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre de la unidad organizativa es obligatorio");
        }
        if (nombre.length() > 200) {
            throw new IllegalArgumentException("El nombre no puede exceder 200 caracteres");
        }
    }

    public void validar() {
        validarNombre();
    }
}