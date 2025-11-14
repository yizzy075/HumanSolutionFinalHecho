package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class PermisoSistemaDomain extends Domain {

    private String nombre;

    private PermisoSistemaDomain(UUID id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public static PermisoSistemaDomain create(UUID id, String nombre) {
        return new PermisoSistemaDomain(id, nombre);
    }

    public static PermisoSistemaDomain create() {
        return new PermisoSistemaDomain(UUIDHelper.getDefaultUUID(), TextHelper.EMPTY);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public void validarNombre() {
        if (TextHelper.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre del permiso de sistema es obligatorio");
        }
        if (nombre.length() > 300) {
            throw new IllegalArgumentException("El nombre no puede exceder 300 caracteres");
        }
    }

    public void validar() {
        validarNombre();
    }
}